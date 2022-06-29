/**
 * Classe que define o modelo Carro, seus atributos e suas respectivas restrições de integridade
 * @author Gabriel Peixto
 */

package com.gabrielpeixoto.parking2.entity;

import com.gabrielpeixoto.parking2.enums.TypeCar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car {
    //Coluna que será a chave primária da tabela
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;

    //Atributos da tabela, com seus nomes e restrições de integridade
    @Column(name = "car_brand", nullable = false)
    private String brand;

    @Column(name = "car_model", nullable = false)
    private String model;

    @Column(name = "car_year")
    private Integer year;

    @Column(name = "car_identity", nullable = false)
    private String identity;

    @Enumerated(EnumType.STRING)
    @Column(name = "car_type")
    TypeCar typeCar;

    //Chaev estrangeira para referenciar o dono do carro na tabela User
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    //Chave estrangeira para referenciar as entradas e saídas do estacionamento na tabela EntranceAndRegistry
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    private List<EntranceAndExitRegistry> registries;
}
