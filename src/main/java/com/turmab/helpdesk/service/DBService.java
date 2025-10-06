package com.turmab.helpdesk.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.turmab.helpdesk.domain.Chamado;
import com.turmab.helpdesk.domain.Cliente;
import com.turmab.helpdesk.domain.Tecnico;
import com.turmab.helpdesk.domain.enums.Perfil;
import com.turmab.helpdesk.domain.enums.Prioridade;
import com.turmab.helpdesk.domain.enums.Status;
import com.turmab.helpdesk.repositories.ChamadoRepository;
import com.turmab.helpdesk.repositories.ClienteRepository;
import com.turmab.helpdesk.repositories.TecnicoRepository;

/**
 * Classe responsável por inicializar o banco de dados com dados de exemplo.
 * 
 * <p>Essa classe é utilizada normalmente em ambiente de desenvolvimento
 * para popular a base de dados com informações iniciais de teste, como
 * técnicos, clientes e chamados.</p>
 * 
 * <p>Ela é executada geralmente na inicialização da aplicação através
 * do método {@link #instanciaDB()}.</p>
 * 
 * @author Gabriel Samilo
 * @version 1.0
 */
@Service
public class DBService {
	
	/** Repositório responsável pela persistência da entidade {@link Tecnico}. */
	@Autowired
	private TecnicoRepository tecnicorepository;
	
	/** Repositório responsável pela persistência da entidade {@link Cliente}. */
	@Autowired
	private ClienteRepository clienterepository;
	
	/** Repositório responsável pela persistência da entidade {@link Chamado}. */
	@Autowired
	private ChamadoRepository chamadorepository;
	
	/** Utilitário de criptografia usado para codificar senhas dos usuários. */
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	 /**
     * Popula o banco de dados com dados iniciais de exemplo.
     * 
     * <p>Este método cria e salva um técnico, um cliente e um chamado
     * relacionados entre si. As senhas são criptografadas utilizando
     * {@link BCryptPasswordEncoder}.</p>
     * 
     * <p>Os objetos criados são:</p>
     * <ul>
     *   <li>Um técnico com perfil de ADMINISTRADOR.</li>
     *   <li>Um cliente com dados básicos de identificação.</li>
     *   <li>Um chamado associado ao técnico e ao cliente.</li>
     * </ul>
     */
	public void instanciaDB() {
		
		// Criação do técnico com perfil ADMIN
		Tecnico tec1 = new Tecnico(null, "Bill Gates", "76045777093", "bill@mail.com", encoder.encode("123"));
		tec1.addPerfil(Perfil.ADMIN);
		
		// Criação do cliente
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "70511744013", "linus@mail.com", "123");
		
		// Criação do chamado associado ao técnico e cliente
		Chamado cha1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		
		// Persistência dos dados
		tecnicorepository.saveAll(Arrays.asList(tec1));
		
		clienterepository.saveAll(Arrays.asList(cli1));
		
		chamadorepository.saveAll(Arrays.asList(cha1));
		
	}
}
