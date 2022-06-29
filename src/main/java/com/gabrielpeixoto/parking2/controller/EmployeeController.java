/**
 * Gerencia as requisições feitas para a rota /app/v1/employee
 * @author Gabriel Peixoto
 */

package com.gabrielpeixoto.parking2.controller;

import com.gabrielpeixoto.parking2.entity.Employee;
import com.gabrielpeixoto.parking2.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/v1/employee")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {
    private EmployeeRepository employeeRepository;

    //obtém a view correspondente para a página
    @GetMapping()
    public String goToPage(Model model)
    {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee";
    }

    /**
     * Cadastra o empregado no banco de dados
     * @param employee o empregado a ser cadastrado
     * @return redirecionamento para o menu principal
     */
    @PostMapping("/new")
    public String saveEmployee(@ModelAttribute("employee") Employee employee)
    {
        employeeRepository.save(employee);
        return "redirect:/";
    }
}
