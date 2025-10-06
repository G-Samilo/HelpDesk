package com.turmab.helpdesk.domain.enums;

/**
 * Enumeração que representa a prioridade de um Chamado no sistema HelpDesk.
 * Define três níveis de prioridade: BAIXA, MÉDIA e ALTA, cada uma com um código e descrição.
 * 
 * @author: Gabriel Samilo
 */
public enum Prioridade {
    BAIXA(0, "BAIXA"),
    MEDIA(1, "MÉDIA"),
    ALTA(2, "ALTA");

    private Integer codigo;
    private String descricao;

    /**
     * Construtor do enum Prioridade.
     * 
     * @param codigo Código numérico da prioridade.
     * @param descricao Descrição textual da prioridade.
     */
    Prioridade(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    /**
     * Retorna o código da prioridade.
     * 
     * @return Código numérico da prioridade.
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Retorna a descrição da prioridade.
     * 
     * @return Descrição textual da prioridade.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Converte um código inteiro em uma instância do enum Prioridade.
     * 
     * @param cod Código numérico da prioridade.
     * @return Instância do enum correspondente ao código, ou null se o código for null.
     * @throws IllegalArgumentException se o código não corresponder a nenhuma prioridade válida.
     */
    public static Prioridade toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Prioridade x : Prioridade.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Prioridade inválida: " + cod);
    }
}