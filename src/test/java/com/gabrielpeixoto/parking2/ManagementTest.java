/**
 * Classe para execução de alguns testes simples
 * @author Gabriel Peixoto
 */

package com.gabrielpeixoto.parking2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielpeixoto.parking2.controller.ManagementController;
import com.gabrielpeixoto.parking2.entity.Car;
import com.gabrielpeixoto.parking2.entity.Employee;
import com.gabrielpeixoto.parking2.entity.EntranceAndExitRegistry;
import com.gabrielpeixoto.parking2.repository.CarRepository;
import com.gabrielpeixoto.parking2.repository.EmployeeRepository;
import com.gabrielpeixoto.parking2.repository.EntranceRegistryRepository;
import com.gabrielpeixoto.parking2.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ManagementTest {

    //Injeção e mock das dependências
    @MockBean
    private ManagementController managementController;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private EntranceRegistryRepository entranceRegistryRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * Converte um objeto para formato JSON
     * @param obj o objeto a ser convertido
     * @return uma string com o objeto no formato JSON
     */
    public static String toJson(final Object obj)
    {
        try
        {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Teste de cadastro de novo empregado
     * @throws Exception
     */
    @Test
    public void shouldRegisterNewEmployee() throws Exception
    {
        Employee e = new Employee("Joaozinho", "12345");
        this.mockMvc.perform(post("/app/v1/employee/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(e)))
                .andExpect(status().is3xxRedirection());
    }

    /**
     * Teste de cadastro incorreto de empregado
     * @throws Exception
     */
    @Test
    public void shouldNotRegisterNewEmployee() throws Exception
    {
        Employee e = new Employee(null, "22222");
        this.mockMvc.perform(post("/app/v1/employee/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(e)))
                .andExpect(status().is5xxServerError());
    }

    /**
     * Teste para configurar carro inexistente como RESIDENT
     * @throws Exception
     */
    @Test
    public void testSetInexistentResidentCar() throws Exception
    {
        String idx = "xcvb";
        this.mockMvc.perform(post("/app/v1/management/adicionaResidente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(idx)))
                .andExpect(status().is5xxServerError());
    }

    /**
     * Testa processo de saída do estacionamento
     * @throws Exception
     */
    @Test
    public void testexitProcess() throws Exception
    {
        String id = "abc123";
        this.mockMvc.perform(post("/app/v1/management/exit/process")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(id)))
                .andExpect(status().is3xxRedirection());
    }

    /**
     * Testa processo de saída de carro inexistente
     * @throws Exception
     */
    @Test
    public void testErrorExitProcess() throws Exception
    {
        String idx = "zxcvb";
        this.mockMvc.perform(post("/app/v1/management/exit/process")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(idx)))
                .andExpect(status().is5xxServerError());
    }

    /**
     * Testa registro de entrada de carro
     * @throws Exception
     */
    @Test
    public void testSaveEntranceRegistry() throws Exception
    {
        EntranceAndExitRegistry registry = new EntranceAndExitRegistry();
        this.mockMvc.perform(post("/app/v1/management/entrance/new?carId=abc123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(registry)))
                .andExpect(status().is3xxRedirection());
    }
}
