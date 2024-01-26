package br.com.cannysters.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cannysters.controllers.BookController;
import br.com.cannysters.data.vo.v1.BookVO;
import br.com.cannysters.exceptions.RequiredObjectIsNullException;
import br.com.cannysters.exceptions.ResourceNotFoundException;
import br.com.cannysters.mapper.Mapper;
import br.com.cannysters.model.Book;
import br.com.cannysters.repository.BookRepository;

@Service
public class BookServices {

	@Autowired
	private BookRepository repository;
	
	private Logger logger = Logger.getLogger(BookServices.class.getName());
	
	public List<BookVO> findAll(){
		logger.info("Finding all Books!");
		
		var listBooks = Mapper.parseListObjects(repository.findAll(), BookVO.class);
		listBooks.stream()
			.forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
		return listBooks;
	}
	
	public BookVO  findById(Long id) {
		logger.info("Finding one Book!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		BookVO vo = Mapper.parseObject(entity, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public BookVO create(BookVO bookVO) {
		
		if(bookVO == null) throw new RequiredObjectIsNullException();
		logger.info("Creating new Book!");
		
		var entity = Mapper.parseObject(bookVO, Book.class);
		var vo = Mapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public BookVO update(BookVO book) {
		if(book == null) throw new RequiredObjectIsNullException();
		logger.info("Updating Book!");
		
		var entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		var vo = Mapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deleting Book!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
}
