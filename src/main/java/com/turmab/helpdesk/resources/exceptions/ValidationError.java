package com.turmab.helpdesk.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

import com.turmab.helpdesk.service.exceptions.FieldMessage;
import com.turmab.helpdesk.service.exceptions.StandardError;


/**
 * Classe de erro personalizada para representar exceções de validação de campos.
 *
 * <p>
 * Essa classe estende {@link StandardError} e adiciona uma lista de objetos
 * {@link FieldMessage}, permitindo detalhar múltiplos erros de validação
 * ocorridos em uma única requisição.
 * </p>
 *
 * <p>
 * É usada principalmente pelo {@code ResourceExceptionHandler} para construir
 * respostas estruturadas em casos de falha de validação de dados
 * (como erros detectados por anotações {@code @Valid}).
 * </p>
 *
 * <p>Exemplo de retorno JSON:</p>
 * <pre>
 * {
 *   "timestamp": 1696545300000,
 *   "status": 400,
 *   "error": "Fields Validation Error",
 *   "message": "Erro na validação dos campos",
 *   "path": "/clientes",
 *   "errors": [
 *       { "fieldName": "email", "message": "E-mail inválido" },
 *       { "fieldName": "cpf", "message": "CPF já cadastrado" }
 *   ]
 * }
 * </pre>
 * 
 * @author Gabriel Samilo
 * @version 1.0
 */
public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	/** Lista de mensagens de erro relacionadas aos campos validados. */
	private List<FieldMessage> errors = new ArrayList<>();

	/**
     * Construtor padrão.
     * <p>Necessário para serialização e uso genérico em respostas JSON.</p>
     */
	public ValidationError() {
		super();
	}

	/**
     * Construtor completo.
     *
     * @param timestamp Momento em que o erro ocorreu (em milissegundos)
     * @param status Código HTTP associado ao erro
     * @param error Tipo ou título do erro
     * @param message Mensagem descritiva sobre o erro
     * @param path Caminho (URI) da requisição que gerou o erro
     */
	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	/**
     * Obtém a lista de erros de campo associados à validação.
     *
     * @return Lista de {@link FieldMessage}
     */
	public List<FieldMessage> getErrors() {
		return errors;
	}

	/**
     * Adiciona um novo erro de campo à lista.
     *
     * @param fieldName Nome do campo que apresentou erro
     * @param message Mensagem explicando o motivo do erro
     */
	public void addErrors(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}

}