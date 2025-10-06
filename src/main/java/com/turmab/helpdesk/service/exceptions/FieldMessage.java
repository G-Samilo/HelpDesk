package com.turmab.helpdesk.service.exceptions;

import java.io.Serializable;

/**
 * Classe auxiliar utilizada para representar mensagens de erro
 * relacionadas à validação de campos específicos.
 *
 * <p>
 * Essa classe é usada principalmente em conjunto com o
 * {@code ResourceExceptionHandler} e {@code ValidationError}
 * para construir respostas personalizadas quando ocorre uma
 * falha de validação (ex.: campos inválidos, formatos incorretos
 * ou valores obrigatórios ausentes).
 * </p>
 *
 * <p>
 * Cada instância de {@link FieldMessage} armazena o nome do campo
 * que gerou o erro e a mensagem explicando o motivo da falha.
 * </p>
 *
 * <p>
 * Implementa {@link Serializable} para permitir sua serialização
 * nas respostas JSON enviadas ao cliente.
 * </p>
 * 
 * @author Gabriel Samilo
 * @version 1.0
 */
public class FieldMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Nome do campo onde ocorreu o erro de validação. */
	private String fieldName;
	
	/** Mensagem descritiva explicando o erro ocorrido. */
	private String message;

	/**
     * Construtor padrão.
     * <p>Necessário para a serialização/deserialização JSON.</p>
     */
	public FieldMessage() {
		super();
	}

	/**
     * Construtor completo.
     *
     * @param fieldName nome do campo com erro
     * @param message mensagem explicando o motivo do erro
     */
	public FieldMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	/**
     * Obtém o nome do campo onde ocorreu o erro.
     *
     * @return nome do campo com erro
     */
	public String getFieldName() {
		return fieldName;
	}

	/**
     * Define o nome do campo onde ocorreu o erro.
     *
     * @param fieldName nome do campo
     */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	 /**
     * Obtém a mensagem explicativa do erro de validação.
     *
     * @return mensagem de erro
     */
	public String getMessage() {
		return message;
	}

	/**
     * Define a mensagem explicativa do erro de validação.
     *
     * @param message mensagem descritiva do erro
     */
	public void setMessage(String message) {
		this.message = message;
	}

}