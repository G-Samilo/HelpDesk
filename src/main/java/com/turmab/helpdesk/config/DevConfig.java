package com.turmab.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.turmab.helpdesk.service.DBService;

/**
 * Classe de configuração específica para o perfil "dev".
 * Inicializa o banco de dados de desenvolvimento caso a propriedade
 * 'spring.jpa.hibernate.ddl-auto' esteja configurada como "create".
 * 
 * @author: Gabriel Samilo
 */
@Configuration
@Profile("dev")
public class DevConfig {
	
	/** Serviço responsável por popular o banco de dados */
	@Autowired
	private DBService dbService;
	
	/** Valor da propriedade ddl-auto do Hibernate */
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;
	
	/**
     * Bean que instancia e popula o banco de dados de desenvolvimento.
     * Executa apenas se o ddl-auto estiver configurado como "create".
     *
     * @return false (sempre retorna false)
     */
	@Bean
	public boolean instaciaDB() {
		if(value.equals("create")) {
			this.dbService.instanciaDB();
		}
		
		return false;
	}
	

}
