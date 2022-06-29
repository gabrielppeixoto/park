/**
 * Gerencia as requisições feitas para a rota /
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
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HomeController {
    private EmployeeRepository employeeRepository;

    //Obtém a view para a página principal
    @GetMapping
    public String homePage(Model model)
    {
        List<Employee> employees = employeeRepository.listAllEmployees();
        model.addAttribute("employees", employees);
        model.addAttribute("index");
        return "index";
    }
}
