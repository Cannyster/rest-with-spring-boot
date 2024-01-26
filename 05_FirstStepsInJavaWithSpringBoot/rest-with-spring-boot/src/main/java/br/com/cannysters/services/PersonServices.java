package br.com.cannysters.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.cannysters.controllers.PersonController;
import br.com.cannysters.data.vo.v1.PersonVO;
import br.com.cannysters.exceptions.RequiredObjectIsNullException;
import br.com.cannysters.exceptions.ResourceNotFoundException;
import br.com.cannysters.mapper.Mapper;
import br.com.cannysters.model.Person;
import br.com.cannysters.repository.PersonRepository;


@Service
public class PersonServices {
	
	@Autowired
	private PersonRepository repository;

	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	public List<PersonVO> findAll() {
		logger.info("Finding all Persons!");
		var persons = Mapper.parseListObjects(repository.findAll(), PersonVO.class);
		persons.stream()
			.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));

		return persons;
	}

	public PersonVO findById(Long id) {
		logger.info("Finding one person!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		PersonVO vo = Mapper.parseObject(entity, PersonVO.class); // criando um objeto de PersonVO para ser manipulado
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel()); //adicão do link ao PersonVO
		return vo;
	}
	
	public PersonVO create(PersonVO personVo) {
		
		if (personVo == null) throw new RequiredObjectIsNullException();
		logger.info("Creating new person!");
		
		var entity = Mapper.parseObject(personVo, Person.class); //Convertendo de PersonVo para Person 
		var vo = Mapper.parseObject(repository.save(entity), PersonVO.class); // Convetendo de Person para PersonVo(depois de salvar no repositorio)
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo; // retornando o objeto convertido em PersonVo como resposta da requisição
	}
	
	
	public PersonVO update(PersonVO person) {
		if (person == null) throw new RequiredObjectIsNullException();
		logger.info("Updating person!");
		
		var entity = repository.findById(person.getKey())
			.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo = Mapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deleting person!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
}
