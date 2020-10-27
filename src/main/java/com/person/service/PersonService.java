package com.person.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.person.exception.ResourceNotFoundException;
import com.person.model.Person;
import com.person.repository.PersonRepository;

@Component
public class PersonService {

	@Autowired
	PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {

		this.personRepository = personRepository;
	}

	public List<Person> getAllPersons() {
		List<Person> personList = personRepository.findAll();
		return personList;
	}

	public Person getPersonById(Long personId) throws ResourceNotFoundException {

		Person personDetails = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));
		return personDetails;

	}

	public Person createPerson(Person person) {

		return personRepository.save(person);
	}

	public Person updatePerson(Long personId, Person person) throws ResourceNotFoundException {

		Person personDetails = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));

		personDetails.setCurrentAddress(person.getCurrentAddress());
		personDetails.setLastName(person.getLastName());
		personDetails.setFirstName(person.getFirstName());
		personDetails.setDateOfBirth(person.getDateOfBirth());
		final Person updatedPerson = personRepository.save(personDetails);
		return updatedPerson;

	}

	public Map<String, Boolean> deletePerson(Long personId) throws ResourceNotFoundException {
		Person person = personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));

		personRepository.delete(person);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	public Person getPersonByNameAnd(String firstName, String lastName) {
		Person person = personRepository.findByFirstNameAndLastName(firstName, lastName);
		return person;
	}

	public Person getPersonByNameOr(String firstName, String lastName) {
		Person person = personRepository.findByFirstNameOrLastName(firstName, lastName);
		return person;
	}
}