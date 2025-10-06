package com.turmab.helpdesk;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Helpdesk.
 * Responsável por inicializar o contexto Spring Boot.
 * Implementa CommandLineRunner, permitindo executar código
 * após o carregamento da aplicação (caso necessário).
 * 
 * @author: Gabriel Samilo
 */
@SpringBootApplication
public class HelpdeskturmabApplication implements CommandLineRunner {
	
	/**
     * Ponto de entrada da aplicação.
     *
     * @param args Argumentos de linha de comando (opcional)
     */
	public static void main(String[] args) {
		SpringApplication.run(HelpdeskturmabApplication.class, args);
	}
	
	/**
     * Método executado após o contexto da aplicação ser carregado.
     * Atualmente não realiza nenhuma ação, mas pode ser usado
     * para inicializações futuras.
     *
     * @param args Argumentos de linha de comando
     * @throws Exception Caso ocorra algum erro na execução
     */
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
