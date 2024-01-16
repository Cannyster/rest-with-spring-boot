package br.com.cannysters.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cannysters.exceptions.ResourceNotFoundException;
import br.com.cannysters.model.Person;
import br.com.cannysters.repository.PersonRepository;


@Service
public class PersonService {
	
	@Autowired
	private PersonRepository repository;

	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public List<Person> findAll() {
		
		logger.info("Finding all persons!");
		
		return repository.findAll() ;
	}

	public Person findById(Long id) {
		
		logger.info("Finding one person!");
		
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	}
	
	public Person create(Person person) {
		
		logger.info("Creating new person!");
		
		return repository.save(person);
	}
	
	public Person update(Person person) {
		
		logger.info("Updating person!");
		
		Person entity = repository.findById(person.getId())
			.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return repository.save(person);
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting person!");
		
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		repository.delete(entity);
	}
	
	
}
