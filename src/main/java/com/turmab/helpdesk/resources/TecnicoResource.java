package com.turmab.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.turmab.helpdesk.domain.Tecnico;
import com.turmab.helpdesk.domain.dtos.TecnicoDTO;
import com.turmab.helpdesk.service.TecnicoService;

@RestController // indica que a classe é um controlador rest
@RequestMapping(value = "/tecnicos") // indica que esta classe é um endpont e já indica o seu caminho de acesso na
										// URL, ex: 'localhost:8080/tecnicos'
public class TecnicoResource {

	@Autowired
	private TecnicoService service; // considerando que a classe de serviço e os métodos já estão criados.

	@GetMapping(value = "/{id}") // estou informando que estou recebendo uma variável de path
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {

		Tecnico obj = service.findById(id);

		return ResponseEntity.ok().body(new TecnicoDTO(obj));

	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {
	    List<TecnicoDTO> list = service.findAll();
	    return ResponseEntity.ok().body(list);
	}




}