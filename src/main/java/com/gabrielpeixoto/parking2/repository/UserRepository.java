/**
 * Interface que contém as consultas ao banco de dados para a tabela User
 * @author Gabriel Peixoto
 */

package com.gabrielpeixoto.parking2.repository;

import com.gabrielpeixoto.parking2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //seleciona todos os usuários
    @Query(value = "select * from users", nativeQuery = true)
    public List<User> listAllUsers();
}
