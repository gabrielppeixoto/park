/**
 * Classe que define a relação Entradas e Saídas (do estacionamento), seus atributos e suas respectivas restrições de integridade
 * @author Gabriel Peixto
 */

package com.gabrielpeixoto.parking2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EntranceAndExitRegistry {
    //Coluna que será a chave primária da tabela
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Atributos da tabela, com seus nomes e restrições de integridade
    //Chave estrangeira para referenciar o carro que terá a entrada e saída registrada
    @JoinColumn(name = "car_entrance_id", referencedColumnName = "car_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;

    @Column(name = "entrance_date")
    private Calendar entranceDate;

    @Column(name = "exit_date")
    private Calendar exitDate;

    //Campo só utilizado por carros do tipo RESIDENT
    private Double accumulatedMinutes;
}
