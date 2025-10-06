package com.turmab.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.turmab.helpdesk.service.DBService;

/**
 * Classe de configuração específica para o perfil "test".
 * Inicializa o banco de dados de teste chamando o serviço de instanciação.
 * 
 * @author: Gabriel Samilo
 */
@Configuration
@Profile("test")
public class TestConfig {
	
	/** Serviço responsável por popular o banco de dados */
	@Autowired
	private DBService dbService;
	
	/**
     * Bean que instancia e popula o banco de dados de teste.
     * Executa sempre que o perfil "test" estiver ativo.
     */
	@Bean
	public void instaciaDB() {
		this.dbService.instanciaDB();
	}
	

}
