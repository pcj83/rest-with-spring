package com.paulocezar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paulocezar.converter.DozerConverter;
import com.paulocezar.converter.custom.PersonConverter;
import com.paulocezar.data.model.Person;
import com.paulocezar.data.vo.v1.PersonVO;
import com.paulocezar.data.vo.v2.PersonVOV2;
import com.paulocezar.exception.ResourceNotFoundException;
import com.paulocezar.repository.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private PersonConverter converter;

	public PersonVO create(PersonVO person) {
		Person entity = DozerConverter.parseObject(person, Person.class);
		PersonVO vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	public PersonVOV2 createV2(PersonVOV2 person) {
		Person entity = converter.convertVOToEntity(person);
		PersonVOV2 vo = converter.convertEntityToVO(repository.save(entity));
		return vo;
	}

	public Page<PersonVO> findAll(Pageable pageable) {
		Page<Person> page = repository.findAll(pageable);
		return  page.map(this::convertToPersonVO);
	}	

	public Page<PersonVO> findPersonByName(String firstName ,Pageable pageable) {
		Page<Person> page = repository.findPersonByName(firstName,pageable);
		return  page.map(this::convertToPersonVO);
	}	
	
	private PersonVO convertToPersonVO(Person entity) {
		return DozerConverter.parseObject(entity, PersonVO.class);
	}


	public PersonVO findById(Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
	}

	public PersonVO update(PersonVO person) {
		Person entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		person = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return person;
	}
	
	@Transactional
	public PersonVO disablePerson(Long id) {
		repository.disablePersons(id);
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
	}
	public void delete(long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

}
