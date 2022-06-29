/**
 * Classe que define modelo Usuário (do estacionamento), seus atributos e suas respectivas restrições de integridade
 * @author Gabriel Peixto
 */

package com.gabrielpeixoto.parking2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    //Coluna que será a chave primária da tabela
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    //Atributos da tabela, com seus nomes e restrições de integridade
    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_cpf", nullable = false)
    private String cpf;

    //Chave estrangeira para referenciar o carro do qual este usuário é dono
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;
}
