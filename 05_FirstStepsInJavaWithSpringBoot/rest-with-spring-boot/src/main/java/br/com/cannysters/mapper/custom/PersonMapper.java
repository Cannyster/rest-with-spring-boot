package br.com.cannysters.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.cannysters.data.vo.v2.PersonVOV2;
import br.com.cannysters.model.Person;

@Service
public class PersonMapper {

	public PersonVOV2 convertEntityToVO(Person person) {
		PersonVOV2 vo = new PersonVOV2();
		vo.setId(person.getId());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setAddress(person.getAddress());
		vo.setBirthDate(new Date());
		vo.setGender(person.getGender());
		return vo;
	}
	
	public Person convertVOToEntity(PersonVOV2 person) {
		Person entity = new Person();
		entity.setId(person.getId());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		//vo.setBirthDate(new Date());
		entity.setGender(person.getGender());
		return entity;
	}
	
	
	
	
}
