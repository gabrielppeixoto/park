/**
 * Gerencia as requisições feitas para a rota /app/v1/management
 * @author Gabriel Peixoto
 */

package com.gabrielpeixoto.parking2.controller;

import com.gabrielpeixoto.parking2.entity.Car;
import com.gabrielpeixoto.parking2.entity.EntranceAndExitRegistry;
import com.gabrielpeixoto.parking2.enums.TypeCar;
import com.gabrielpeixoto.parking2.repository.CarRepository;
import com.gabrielpeixoto.parking2.repository.EntranceRegistryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/app/v1/management")
@AllArgsConstructor
public class ManagementController {

    //Injeção de dependências
    private CarRepository repository;
    private EntranceRegistryRepository registryRepository;

    //A partir daqui, todas as funções do tipo 'goTo' ou 'get' retornam o template da página que se deseja acessar

    @GetMapping
    public String getPage(Model model)
    {
        model.addAttribute("management");
        return "management";
    }

    //Métodos relacionados a registro de entradas
    @GetMapping("/entrance")
    public String goToRegistries(Model model)
    {
        List<String> carIdentities = repository.listCarIdentitiesNotNull();
        model.addAttribute("carIdentities", carIdentities);
        model.addAttribute("entranceRegistry");
        return "entranceRegistry";
    }

    /**
     * Salva o momento de entrada para determinado carro
     * @param entranceAndExitRegistry contém os dados fornecidos na view
     * @param cid id do carro que se deseja cadastrar a entrada
     * @return
     */
    @PostMapping("/entrance/new")
    public String saveEntranceRegistry(@Valid @ModelAttribute("entranceRegistry") EntranceAndExitRegistry entranceAndExitRegistry,
                                       @RequestParam(value = "carId") String cid)
    {
        entranceAndExitRegistry.setAccumulatedMinutes(0.0); //Atributo só utilizado para carros residentes
        entranceAndExitRegistry.setEntranceDate(Calendar.getInstance());
        Car c = repository.listCarByIdentity(cid);
        entranceAndExitRegistry.setCar(c);
        registryRepository.save(entranceAndExitRegistry);
        return "redirect:/app/v1/management";
    }

    //Métodos relacionados a registro de saída
    @GetMapping("/exit")
    public String goToExitRegistries(Model model)
    {
        List<String> carIdentities = repository.listCarIdentitiesWithEntranceNotNull();
        model.addAttribute("carIdentities", carIdentities);
        model.addAttribute("exitRegistry");
        return "exitRegistry";
    }

    /**
     * Ob´tem o horário de saída e calcula o tempo total que determinado carro ocupou no estacionamento
     * Para carros residentes, adiciona esse tempo ao total mensal, que será faturado ao fim do mês
     * Para carros não residentes, já exibe a fatura na tela
     * @param cid   o id do carro cuja saída será processada
     * @return
     */
    @PostMapping("/exit/process")
    public String processExit(@Valid @ModelAttribute("carId") String cid)
    {
        Car c = repository.listCarByIdentity(cid);
        EntranceAndExitRegistry registry = registryRepository.listRegistryByCarIdentity(c.getId().toString());
        registry.setExitDate(Calendar.getInstance());
        //Calcula o tempo gasto no estacionamento, em miutos
        Calendar in = registry.getEntranceDate(), out = registry.getExitDate();
        double difMin = ((out.getTimeInMillis()/1000) - (in.getTimeInMillis()/1000))/60;
        if(c.getTypeCar() == TypeCar.RESIDENT_CAR)
        {
            //Adiciona ao total mensal, o tempo gasto no estacionamento
            registry.setAccumulatedMinutes(registry.getAccumulatedMinutes() + difMin);
        }
        else if(c.getTypeCar() == TypeCar.NON_RESIDENT_CAR)
        {
            //Já obtem o total a ser pago
            double amount = difMin*0.5;
            return "redirect:/app/v1/management/exit/process/amount?amount=" + amount;
        }
        registryRepository.save(registry);
        return "redirect:/app/v1/management";
    }

    //Para exibir o valor total na tela, no caso de carros não residentes
    @GetMapping("/exit/process/amount")
    public String showAmount(Model model, @RequestParam(value = "amount") Double amount)
    {
        model.addAttribute("showAmount");
        return "showAmount";
    }

    //Para classificar os carros
    @GetMapping("/adicionaOficial")
    public String goToOfficialPage(Model model)
    {
        List<String> notOfficials = repository.listCarsNotOfficial();
        model.addAttribute("addOfficial");
        model.addAttribute("notOfficials", notOfficials);
        return "addOfficial";
    }

    /**
     * Classifica o carro como oficial
     * @param idt   o id do carro a ser classificado
     * @return string para se redirecionar para o menu
     */
    @PostMapping("/adicionaOficial")
    public String setOfficialCar(@Valid @ModelAttribute("carId") String idt)
    {
        Car c = repository.listCarByIdentity(idt);
        c.setTypeCar(TypeCar.OFFICIAL_CAR);
        repository.save(c);
        return "redirect:/app/v1/management";
    }

    @GetMapping("/adicionaResidente")
    public String goToResidentPage(Model model)
    {
        List<String> notResidents = repository.listCarsNotResident();
        model.addAttribute("addOfficial");
        model.addAttribute("notOfficials", notResidents);
        return "addOfficial";
    }

    /**
     * Classifica o carro como residente
     * @param idt o id do carro a ser classificado
     * @return string para se redirecionar para o menu principal
     */
    @PostMapping("/adicionaResidente")
    public String setResidentCar(@Valid @ModelAttribute("carId") String idt)
    {
        Car c = repository.listCarByIdentity(idt);
        c.setTypeCar(TypeCar.RESIDENT_CAR);
        repository.save(c);
        return "redirect:/app/v1/management";
    }
}
