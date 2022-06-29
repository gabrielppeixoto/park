/**
 * Classe que define o modelo Empregado, seus atributos e suas respectivas restrições de integridade
 * @author Gabriel Peixto
 */

package com.gabrielpeixoto.parking2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    //Coluna que será a chave primária da tabela
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Atributos da tabela, com seus nomes e restrições de integridade
    @Column(name = "employee_name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "employee_ssn", nullable = false)
    @NotBlank
    private String ssn;

    public Employee(String name, String ssn)
    {
        this.name = name;
        this.ssn = ssn;
    }
}
