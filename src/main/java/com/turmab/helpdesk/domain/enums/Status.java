package com.turmab.helpdesk.domain.enums;

/**
 * Enumeração que representa o status de um Chamado no sistema HelpDesk.
 * Define três estados possíveis: ABERTO, EM ANDAMENTO e ENCERRADO, 
 * cada um com um código e descrição.
 * 
 * @author Gabriel Samilo
 */
public enum Status {
    ABERTO(0, "ABERTO"),
    ANDAMENTO(1, "EM ANDAMENTO"),
    ENCERRADO(2, "ENCERRADO");

    private Integer codigo;
    private String descricao;

    /**
     * Construtor do enum Status.
     * 
     * @param codigo Código numérico do status.
     * @param descricao Descrição textual do status.
     */
    Status(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    /**
     * Retorna o código do status.
     * 
     * @return Código numérico do status.
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Retorna a descrição do status.
     * 
     * @return Descrição textual do status.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Converte um código inteiro em uma instância do enum Status.
     * 
     * @param cod Código numérico do status.
     * @return Instância do enum correspondente ao código, ou null se o código for null.
     * @throws IllegalArgumentException se o código não corresponder a nenhum status válido.
     */
    public static Status toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Status x : Status.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Status inválido: " + cod);
    }
}