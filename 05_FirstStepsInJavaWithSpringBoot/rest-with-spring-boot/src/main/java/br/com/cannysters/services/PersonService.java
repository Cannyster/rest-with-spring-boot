package br.com.cannysters.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cannysters.data.vo.v1.PersonVO;
import br.com.cannysters.exceptions.ResourceNotFoundException;
import br.com.cannysters.mapper.DozerMapper;
import br.com.cannysters.model.Person;
import br.com.cannysters.repository.PersonRepository;


@Service
public class PersonService {
	
	@Autowired
	private PersonRepository repository;

	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public List<PersonVO> findAll() {
		logger.info("Finding all persons!");
		
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}

	public PersonVO findById(Long id) {
		logger.info("Finding one person!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		return DozerMapper.parseObject(entity, PersonVO.class);
				
	}
	
	public PersonVO create(PersonVO personVo) {
		logger.info("Creating new person!");
		
		var entity = DozerMapper.parseObject(personVo, Person.class); //Convertendo de PersonVo para Person 
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class); // Convetendo de Person para PersonVo(depois de salvar no repositorio)
		return vo; // retornando o objeto convertido em PersonVo como resposta da requisição
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Updating person!");
		
		var entity = repository.findById(person.getId())
			.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deleting person!");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		repository.delete(entity);
	}
	
	
}
