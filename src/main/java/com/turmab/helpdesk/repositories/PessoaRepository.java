package com.turmab.helpdesk.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.turmab.helpdesk.domain.Pessoa;

/**
 * Repositório JPA para a entidade Pessoa.
 * Fornece operações CRUD básicas e consultas personalizadas para Pessoa.
 * 
 * Estende JpaRepository, que já inclui métodos como:
 * - findAll()
 * - findById(Integer id)
 * - save(Pessoa entity)
 * - deleteById(Integer id)
 * 
 * Métodos personalizados:
 * - findByCpf(String cpf): busca uma Pessoa pelo CPF.
 * - findByEmail(String email): busca uma Pessoa pelo email.
 * 
 * @author: Gabriel Samilo
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	Optional<Pessoa> findByCpf(String cpf);

	Optional<Pessoa> findByEmail(String email);

}
