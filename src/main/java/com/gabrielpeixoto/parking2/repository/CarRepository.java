/**
 * Interface que contém as consultas ao banco de dados para a tabela Car
 * @author Gabriel Peixoto
 */

package com.gabrielpeixoto.parking2.repository;

import com.gabrielpeixoto.parking2.entity.Car;
import com.gabrielpeixoto.parking2.entity.EntranceAndExitRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    //seleciona todos os carros
    @Query(value = "select * from car", nativeQuery = true)
    public List<Car> listAllCars();

    //seleciona os carros que possuem donos cadastrados
    @Query(value = "select car_identity from car where user_id is NOT null", nativeQuery = true)
    public List<String> listCarIdentitiesNotNull();

    //seleciona o carro com a dada placa
    @Query(value = "select * from car c where c.car_identity = ?1", nativeQuery = true)
    public Car listCarByIdentity(String idt);

    //lista os carros que já entraram no estacionamento e que estão cadastrados no sistema
    @Query(value = "select distinct c.car_identity from car c natural join entrance_and_exit_registry e where e.entrance_date is not null and e.car_entrance_id is not null", nativeQuery = true)
    public List<String> listCarIdentitiesWithEntranceNotNull();

    //seleciona carros não oficiais
    @Query(value = "select c.car_identity from car c where c.car_type not like 'OFFICIAL_CAR'", nativeQuery = true)
    public List<String> listCarsNotOfficial();

    //seleciona carros não residentes
    @Query(value = "select c.car_identity from car c where c.car_type not like 'RESIDENT_CAR'", nativeQuery = true)
    public List<String> listCarsNotResident();
}
