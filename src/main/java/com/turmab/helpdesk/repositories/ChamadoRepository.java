package com.turmab.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turmab.helpdesk.domain.Chamado;

/**
 * Repositório JPA para a entidade Chamado.
 * Fornece operações CRUD básicas e consultas personalizadas para Chamado.
 * 
 * Extende JpaRepository, que já inclui métodos como:
 * - findAll()
 * - findById(Integer id)
 * - save(Chamado entity)
 * - deleteById(Integer id)
 * 
 * @author: Gabriel
 */
@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
