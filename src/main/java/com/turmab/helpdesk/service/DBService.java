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

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicorepository;
	@Autowired
	private ClienteRepository clienterepository;
	@Autowired
	private ChamadoRepository chamadorepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void instanciaDB() {
		
		Tecnico tec1 = new Tecnico(null, "Bill Gates", "76045777093", "bill@mail.com", encoder.encode("123"));
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "70511744013", "linus@mail.com", "123");
		
		Chamado cha1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		
		tecnicorepository.saveAll(Arrays.asList(tec1));
		
		clienterepository.saveAll(Arrays.asList(cli1));
		
		chamadorepository.saveAll(Arrays.asList(cha1));
		
	}
}
