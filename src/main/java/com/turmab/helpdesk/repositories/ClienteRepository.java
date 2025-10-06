package com.turmab.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turmab.helpdesk.domain.Cliente;

/**
 * Repositório JPA para a entidade Cliente.
 * Fornece operações CRUD básicas e consultas personalizadas para Cliente.
 * 
 * Estende JpaRepository, que já inclui métodos como:
 * - findAll()
 * - findById(Integer id)
 * - save(Cliente entity)
 * - deleteById(Integer id)
 * 
 * @author: Gabriel
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
