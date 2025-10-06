package com.turmab.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turmab.helpdesk.domain.Tecnico;

/**
 * Repositório JPA para a entidade Tecnico.
 * Fornece operações CRUD básicas e consultas personalizadas para Tecnico.
 * 
 * Estende JpaRepository, que já inclui métodos como:
 * - findAll()
 * - findById(Integer id)
 * - save(Tecnico entity)
 * - deleteById(Integer id)
 * 
 * @author: Gabriel Samilo
 */
@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
