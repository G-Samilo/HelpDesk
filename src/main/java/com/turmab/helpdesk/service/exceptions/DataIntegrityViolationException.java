package com.turmab.helpdesk.service.exceptions;

/**
 * Exceção personalizada lançada quando ocorre uma violação de integridade de dados.
 *
 * <p>
 * Essa exceção é utilizada para representar situações em que uma operação no banco de dados
 * viola restrições de integridade, como tentativa de inserir registros duplicados,
 * exclusão de entidades vinculadas ou conflito de CPF/E-mail já cadastrados.
 * </p>
 *
 * <p>
 * É uma subclasse de {@link RuntimeException}, permitindo que seja lançada sem a necessidade
 * de tratamento obrigatório (unchecked exception).
 * </p>
 *
 * <p>
 * Geralmente é capturada e tratada pela classe {@code ResourceExceptionHandler},
 * retornando uma resposta padronizada para o cliente da API.
 * </p>
 * 
 * @author Gabriel Samilo
 * @version 1.0
 */
public class DataIntegrityViolationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	/**
     * Construtor que permite especificar uma mensagem detalhada e a causa original do erro.
     *
     * @param message mensagem descritiva da exceção
     * @param cause causa original que gerou a exceção
     */
	public DataIntegrityViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
     * Construtor que permite especificar apenas a mensagem descritiva da exceção.
     *
     * @param message mensagem explicando o motivo da exceção
     */
	public DataIntegrityViolationException(String message) {
		super(message);
	}


}