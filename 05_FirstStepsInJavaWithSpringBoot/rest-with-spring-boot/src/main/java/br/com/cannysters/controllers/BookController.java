package br.com.cannysters.controllers;

import java.util.List;

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

import br.com.cannysters.data.vo.v1.BookVO;
import br.com.cannysters.data.vo.v1.PersonVO;
import br.com.cannysters.services.BookServices;
import br.com.cannysters.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Book", description = "Endpoints for Managing Books")
public class BookController {
	
	@Autowired
	private BookServices service;

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML , MediaType.APPLICATION_YML} )
	@Operation( summary = "Finds all Books", description = "Finds all Books", tags = {"Book"}, 
	responses = {	
			@ApiResponse(description = "Sucess", responseCode = "200", 
					content = 
							@Content(
									mediaType = "application/json", 
									array = @ArraySchema(schema = @Schema(implementation = BookVO.class))
								)
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
		})
	public List<BookVO> findAll() {		
		return service.findAll();
	}	
	
	@GetMapping(value = "/{id}", 
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML , MediaType.APPLICATION_YML})
	@Operation( summary = "Finds a Book", description = "Finds a Book", tags = {"Book"}, 
		responses = {	
			@ApiResponse(description = "Sucess", responseCode = "200", 
					content = @Content(schema = @Schema(implementation = BookVO.class))
			),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
		})
	public ResponseEntity<BookVO> findById(@PathVariable(value = "id")Long id)  {
		BookVO bookVO = service.findById(id);
		return ResponseEntity.ok().body(bookVO);
	}

	@PostMapping(
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  },
			consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
	@Operation( summary = "Adds a new Book", 
		description = "Adds a new Book By passing in a Json, XML or  YML representation of the book!", tags = {"Book"}, 
		responses = {	
				@ApiResponse(description = "Sucess", responseCode = "200", 
						content = @Content(schema = @Schema(implementation = PersonVO.class))
				),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
		})
	public BookVO create (@RequestBody BookVO book) {
		return service.create(book);
	}
	
	@PutMapping(
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  },
			consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
	@Operation( summary = "Updates a Book", 
		description =  "Update a Book By passing in a Json, XML or  YML representation of the book!", tags = {"Book"}, 
		responses = {	
				@ApiResponse(description = "Sucess", responseCode = "200", 
						content = @Content(schema = @Schema(implementation = PersonVO.class))
				),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
		})
	public BookVO update (@RequestBody BookVO book) {
		return service.update(book);
	}
	
	@DeleteMapping(value =  "/{id}")
	@Operation( summary = "Deletes a Book",
		description =  "Deletes a Book By passing in a Json, XML or  YML representation of the book!", tags = {"Book"}, 
		responses = {	
				@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			})
	public ResponseEntity<Void> deleteById (@PathVariable(value = "id") Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	
}
