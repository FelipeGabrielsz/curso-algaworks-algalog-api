package com.algaworks.algalog.domain.repository;

import com.algaworks.algalog.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNome(String nome);
    //Consulta e traz apenas os caracteres, que passamos, que contem nele
    List<Cliente> findByNomeContaining(String nome);
    Optional<Cliente> findByEmail(String email);

}
