/**
 * Enumeração que contém os tipos de carros que utilizam o estacionamento
 * @author Gabriel Peixoto
 */

package com.gabrielpeixoto.parking2.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public enum TypeCar {
    OFFICIAL_CAR, RESIDENT_CAR, NON_RESIDENT_CAR
}
