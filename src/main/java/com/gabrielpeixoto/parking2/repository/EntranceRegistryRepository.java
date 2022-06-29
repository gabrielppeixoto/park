/**
 * Interface que contém as consultas ao banco de dados para a tabela EntranceAndExit
 * @author Gabriel Peixoto
 */

package com.gabrielpeixoto.parking2.repository;

import com.gabrielpeixoto.parking2.entity.EntranceAndExitRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntranceRegistryRepository extends JpaRepository<EntranceAndExitRegistry, Long> {
    //seleciona todos os registros
    @Query(value = "select * from entrance_and_exit_registry", nativeQuery = true)
    public List<EntranceAndExitRegistry> listAllRegistries();

    //seleciona os registros para um dado carro
    @Query(value = "select * from entrance_and_exit_registry where car_entrance_id = ?1", nativeQuery = true)
    public EntranceAndExitRegistry listRegistryByCarIdentity(String id);
}
