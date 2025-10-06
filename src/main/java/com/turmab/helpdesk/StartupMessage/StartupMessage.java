package com.turmab.helpdesk.StartupMessage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Componente que exibe uma mensagem no console quando a aplicação Spring Boot é iniciada.
 * Implementa a interface CommandLineRunner, que permite executar código após o
 * contexto da aplicação ser carregado.
 * 
 * @author: Gabriel Samilo
 */
@Component
public class StartupMessage implements CommandLineRunner {

	/**
     * Método executado automaticamente ao iniciar a aplicação.
     * Exibe uma mensagem de sucesso no console.
     *
     * @param args Argumentos de linha de comando (não utilizados)
     */
    @Override
    public void run(String... args) {
        System.out.println("\n*********************************************");
        System.out.println("* APLICAÇÃO HELPDESK INICIADA COM SUCESSO!  *");
        System.out.println("*********************************************\n");
    }
}