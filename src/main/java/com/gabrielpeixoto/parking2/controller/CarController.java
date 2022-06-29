/**
 * Gerencia as requisições feitas para a rota /app/v1/car
 * @author Gabriel Peixoto
 */

package com.gabrielpeixoto.parking2.controller;

import com.gabrielpeixoto.parking2.entity.Car;
import com.gabrielpeixoto.parking2.entity.Employee;
import com.gabrielpeixoto.parking2.enums.TypeCar;
import com.gabrielpeixoto.parking2.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/v1/car")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarController {
    private CarRepository carRepository;

    //Obtém a view correspondente para essa página
    @GetMapping
    public String getPage(Model model)
    {
        Car car = new Car();
        model.addAttribute("car", car);
        return "car";
    }

    /**
     * Cadastra o carro no banco de dados
     * @param car o carro a ser cadastrado
     * @return redirecionamento para o menu principal
     */
    @PostMapping("/new")
    public String saveCar(@ModelAttribute("car") Car car)
    {
        car.setTypeCar(TypeCar.NON_RESIDENT_CAR);
        carRepository.save(car);
        return "redirect:/app/v1/management";
    }

}
