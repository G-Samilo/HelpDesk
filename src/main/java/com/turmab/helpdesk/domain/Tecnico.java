package com.turmab.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.turmab.helpdesk.domain.enums.Perfil;

/**
 * Entidade Tecnico que representa um técnico do sistema HelpDesk.
 * Herda atributos da classe Pessoa e mantém a lista de chamados atribuídos ao técnico.
 * 
 * Cada técnico possui automaticamente o perfil TECNICO.
 * 
 * @author: Gabriel Samilo
 */
@Entity
public class Tecnico extends Pessoa{

	private static final long serialVersionUID = 1L;
	
	/** Lista de chamados atribuídos ao técnico */
	@OneToMany(mappedBy = "tecnico")
	private List<Chamado> chamados = new ArrayList<>();
	
	/** Construtor padrão */
	public Tecnico() {
		super();
		addPerfil(Perfil.TECNICO);
	}

	/**
     * Construtor com parâmetros para criar um técnico.
     * 
     * @param id Identificador do técnico
     * @param nome Nome do técnico
     * @param cpf CPF do técnico
     * @param email Email do técnico
     * @param senha Senha do técnico
     */
	public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.TECNICO);
	}

	/**
     * Retorna a lista de chamados atribuídos ao técnico.
     * 
     * @return Lista de Chamado atribuídos ao técnico
     */
	public List<Chamado> getChamados() {
		return chamados;
	}

	/**
     * Define a lista de chamados atribuídos ao técnico.
     * 
     * @param chamados Lista de Chamado a ser associada ao técnico
     */
	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

	
	
}