package com.paulocezar.converter.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.paulocezar.data.model.Person;
import com.paulocezar.data.vo.v2.PersonVOV2;


@Service
public class PersonConverter {
	
	public PersonVOV2 convertEntityToVO(Person person) {
		PersonVOV2 vo = new PersonVOV2();
		vo.setId(person.getId());
		vo.setAdress(person.getAdress());
		vo.setBirthday(new Date());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setGender(person.getGender());
		return vo;
	}	
	
	public Person convertVOToEntity(PersonVOV2 person) {
		Person entity = new Person();
		entity.setId(person.getId());
		entity.setAdress(person.getAdress());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		return entity;
	}

}