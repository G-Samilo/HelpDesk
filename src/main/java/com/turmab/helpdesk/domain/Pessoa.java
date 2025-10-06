package com.turmab.helpdesk.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.turmab.helpdesk.domain.enums.Perfil;

/**
 * Classe abstrata que representa uma pessoa no sistema HelpDesk.
 * Serve como superclasse para Cliente e Tecnico.
 * Contém atributos comuns como id, nome, cpf, email, senha, perfis e data de criação.
 * 
 * @author Gabriel Samilo
 */
@Entity
public abstract class Pessoa implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	/** Identificador único da pessoa */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	/** Nome da pessoa */
	protected String nome;
	
	
	/** CPF da pessoa (único) */
	@Column(unique = true)
	protected String cpf;
	
	/** Email da pessoa (único) */
	@Column(unique = true)
	protected String email;
	
	/** Senha da pessoa */
	protected String senha;
	
	/** Conjunto de perfis da pessoa */
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	protected Set<Integer> perfis = new HashSet<>();
	
	/** Data de criação do registro */
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();
	
	/** Construtor padrão */
	public Pessoa() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	/**
     * Construtor com parâmetros.
     * 
     * @param id Identificador da pessoa
     * @param nome Nome da pessoa
     * @param cpf CPF da pessoa
     * @param email Email da pessoa
     * @param senha Senha da pessoa
     */
	public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		addPerfil(Perfil.CLIENTE);
	}

	/** @return o ID da pessoa */
	public Integer getId() {
		return id;
	}

	/** @param id define o ID da pessoa */
	public void setId(Integer id) {
		this.id = id;
	}

	/** @return o nome da pessoa */
	public String getNome() {
		return nome;
	}

	/** @param nome define o nome da pessoa */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/** @return o CPF da pessoa */
	public String getCpf() {
		return cpf;
	}

	/** @param cpf define o CPF da pessoa */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/** @return o email da pessoa */
	public String getEmail() {
		return email;
	}

	/** @param email define o email da pessoa */
	public void setEmail(String email) {
		this.email = email;
	}

	/** @return a senha da pessoa */
	public String getSenha() {
		return senha;
	}

	/** @param senha define a senha da pessoa */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
     * Retorna o conjunto de perfis da pessoa como enum Perfil.
     * 
     * @return Conjunto de Perfis
     */
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	/**
     * Adiciona um perfil à pessoa.
     * 
     * @param perfil Perfil a ser adicionado
     */
	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}

	/** @return a data de criação do registro */
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	/** @param dataCriacao define a data de criação do registro */
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * O método equals verifica se dois objetos são "iguais" com base em seus atributos,
	 * enquanto o hashCode gera um código numérico que representa o objeto,
	 * permitindo que coleções encontrem objetos de forma rápida.
	 *
	 * @return the int
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}