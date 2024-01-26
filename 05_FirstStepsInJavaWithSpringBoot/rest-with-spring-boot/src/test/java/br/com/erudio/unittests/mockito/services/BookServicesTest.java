package br.com.erudio.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.cannysters.data.vo.v1.BookVO;
import br.com.cannysters.exceptions.RequiredObjectIsNullException;
import br.com.cannysters.model.Book;
import br.com.cannysters.repository.BookRepository;
import br.com.cannysters.services.BookServices;
import br.com.cannysters.unittests.mapper.mocks.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

	MockBook input;
	
	@InjectMocks
	private BookServices service;
	
	@Mock
	BookRepository repository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		List<Book> listBook = input.mockEntityList();
		
		when(repository.findAll()).thenReturn(listBook);
		
		var book = service.findAll();
		
		assertNotNull(book);
		assertEquals(14, book.size());
		
		var bookOne = book.get(1);
		
		assertNotNull(bookOne);
		assertNotNull(bookOne.getKey());
		assertNotNull(bookOne.getLinks());
		assertNotNull(bookOne.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Author1", bookOne.getAuthor());
		assertNotNull(bookOne.getLaunchDate());
		assertEquals(1.0, bookOne.getPrice());
		assertEquals("Title1", bookOne.getTitle());
		
		var bookSeven = book.get(7);
		
		assertNotNull(bookSeven);
		assertNotNull(bookSeven.getKey());
		assertNotNull(bookSeven.getLinks());
		assertNotNull(bookSeven.toString().contains("links: [</api/book/v1/7>;rel=\"self\"]"));
		assertEquals("Author7", bookSeven.getAuthor());
		assertNotNull(bookSeven.getLaunchDate());
		assertEquals(7.0, bookSeven.getPrice());
		assertEquals("Title7", bookSeven.getTitle());
		
	}

	@Test
	void testFindById() {
		Book book = input.mockEntity(1);
		book.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(book));
		
		var result = service .findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		System.out.println(result.toString());
		assertNotNull(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Author1", book.getAuthor());
		assertNotNull(book.getLaunchDate());
		assertEquals(1.0, book.getPrice());
		assertEquals("Title1", book.getTitle());
		
	}

	@Test
	void testCreate() {
		Book entity = input.mockEntity(1);
		
		Book persisted = entity;
		entity.setId(1L);
		
		BookVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service .create(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		assertNotNull(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Author1", result.getAuthor());
		assertNotNull(result.getLaunchDate());
		assertEquals(1.0, result.getPrice());
		assertEquals("Title1", result.getTitle());

	}
	
	@Test
	void testCreateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testUpdate() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		Book persisted = entity;
		persisted.setId(1L);
		
		BookVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service .update(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		assertNotNull(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Author1", result.getAuthor());
		assertNotNull(result.getLaunchDate());
		assertEquals(1.0, result.getPrice());
		assertEquals("Title1", result.getTitle());;

	}

	@Test
	void testUpdateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));

	}
	
	@Test
	void testDelete() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		service .delete(1L);
		
	}

}
