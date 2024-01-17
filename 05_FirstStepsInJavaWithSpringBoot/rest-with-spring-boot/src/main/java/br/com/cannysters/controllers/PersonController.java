package br.com.cannysters.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cannysters.data.vo.v1.PersonVO;
import br.com.cannysters.services.PersonService;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@Autowired
	private PersonService service;
	//private PersonService service = new PesonService();@Autowired faz essa construção em tempo de execução(RunTime)
	
	@GetMapping(value = "/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<PersonVO> findById(@PathVariable(value = "id")Long id) {
		PersonVO person = service.findById(id);
		return ResponseEntity.ok().body(person);
	}
	
	@PostMapping(
			produces = MediaType.APPLICATION_JSON_VALUE, // informa que este elemento vai ser exportado para JSON
			consumes = MediaType.APPLICATION_JSON_VALUE) // informa que consome elementos JSON
	public PersonVO create(@RequestBody PersonVO person) {		
		return service.create(person);
	}
	
	@PutMapping(
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE) 
	public PersonVO update(@RequestBody PersonVO person) {		
		return service.update(person);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable(value = "id")Long id) {		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonVO> findAll() {		
		return service.findAll();
	}
	
	
}
