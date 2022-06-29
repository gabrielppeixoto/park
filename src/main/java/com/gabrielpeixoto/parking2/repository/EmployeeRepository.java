/**
 * Interface que contém as consultas ao banco de dados para a tabela Employee
 * @author Gabriel Peixoto
 */

package com.gabrielpeixoto.parking2.repository;

import com.gabrielpeixoto.parking2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //seleciona todos os empregados
    @Query(value = "select * from employee", nativeQuery = true)
    public List<Employee> listAllEmployees();
}
