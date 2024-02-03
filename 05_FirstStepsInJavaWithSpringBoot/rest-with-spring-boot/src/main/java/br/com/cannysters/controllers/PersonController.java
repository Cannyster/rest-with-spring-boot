package br.com.cannysters.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cannysters.data.vo.v1.PersonVO;
import br.com.cannysters.services.PersonServices;
import br.com.cannysters.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {
	
	@Autowired
	private PersonServices service;
	//private PersonService service = new PesonService();@Autowired faz essa construção em tempo de execução(RunTime)
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML , MediaType.APPLICATION_YML} )
	@Operation( summary = "Finds all People", description = "Finds all People", tags = {"People"}, 
	responses = {	
			@ApiResponse(description = "Sucess", responseCode = "200", 
					content = 
							@Content(
									mediaType = "application/json", 
									array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
								)
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
		})
	public List<PersonVO> findAll() {		
		return service.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:8080") // habilitando o cors apenas para local host 8080 nessa requisição apenas
	@GetMapping(value = "/{id}",
		produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  } ) 
	@Operation( summary = "Finds a Person", description = "Finds a Person", tags = {"People"}, 
		responses = {	
				@ApiResponse(description = "Sucess", responseCode = "200", 
						content = @Content(schema = @Schema(implementation = PersonVO.class))
				),
				@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			})
	public ResponseEntity<PersonVO> findById(@PathVariable(value = "id")Long id) {
		PersonVO person = service.findById(id);
		return ResponseEntity.ok().body(person);
	}
	
	
	@CrossOrigin(origins = {"http://localhost:8080", "http://cannysters.com.br"})// cors com 2 origens permitidas
	@PostMapping(
		produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }, // informa que este elemento vai ser exportado para JSON, XML, YAML
		consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }) // informa que consome elementos JSON, XML, YAML
	@Operation( summary = "Adds a new Person", 
		description = "Adds a new Person By passing in a Json, XML or  YML representation of the person!", 
		tags = {"People"}, 
		responses = {	
				@ApiResponse(description = "Sucess", responseCode = "200", 
						content = @Content(schema = @Schema(implementation = PersonVO.class))
				),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			})
	public PersonVO create(@RequestBody PersonVO person) {		
		return service.create(person);
	}
	
	@PutMapping(
		produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  },
		consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  }) 
	@Operation( summary = "Updates a Person", 
		description =  "Update a Person By passing in a Json, XML or  YML representation of the person!", 
		tags = {"People"}, 
		responses = {	
				@ApiResponse(description = "Sucess", responseCode = "200", 
						content = @Content(schema = @Schema(implementation = PersonVO.class))
				),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			})
	public PersonVO update(@RequestBody PersonVO person) {		
		return service.update(person);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation( summary = "Deletes a Person",
			description =  "Deletes a Person By passing in a Json, XML or  YML representation of the person!", 
			tags = {"People"}, 
			responses = {	
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
				})
	public ResponseEntity<Void> deleteById(@PathVariable(value = "id")Long id) {		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}


	
	
}
