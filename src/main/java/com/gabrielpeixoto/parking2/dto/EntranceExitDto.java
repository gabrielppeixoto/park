package com.gabrielpeixoto.parking2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Calendar;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class EntranceExitDto {
    private String carEntranceId;
    private Calendar entranceDate;
    private Calendar exitDate;
}
