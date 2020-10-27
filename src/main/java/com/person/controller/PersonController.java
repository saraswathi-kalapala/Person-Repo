package com.person.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.person.exception.ResourceNotFoundException;
import com.person.model.Person;
import com.person.service.PersonService;

@RestController
@RequestMapping("/requirement/v1")
public class PersonController {

	@Autowired
	PersonService personService;

	@GetMapping("/persons")
	public ResponseEntity<List<Person>> getAllPersons() {
		List<Person> person = personService.getAllPersons();
		return ResponseEntity.ok(person);
	}

	@GetMapping("/persons/{id}")
	public Person getPersonById(@PathVariable(value = "id") Long personId) throws ResourceNotFoundException {
		Person person = personService.getPersonById(personId);
		return person;

	}

	@RequestMapping(value = "/persons", method = RequestMethod.POST, produces = "application/json ", consumes = "application/json")
	public ResponseEntity<Person> createPerson(@Validated @RequestBody Person person) {
		Person createPerson = personService.createPerson(person);
		return ResponseEntity.ok(createPerson);
	}

	@RequestMapping(value = "/persons/{id}", method = RequestMethod.PUT, produces = "application/json ", consumes = "application/json")
	public ResponseEntity<Person> updatePerson(@PathVariable(value = "id") Long personId,
			@Validated @RequestBody Person personDetails) throws ResourceNotFoundException {
		Person updatedPerson = personService.updatePerson(personId, personDetails);
		return ResponseEntity.ok(updatedPerson);
	}

	@DeleteMapping("/persons/{id}")
	public Map<String, Boolean> deletePerson(@PathVariable(value = "id") Long personId)
			throws ResourceNotFoundException {
		Map<String, Boolean> response = personService.deletePerson(personId);
		return response;
	}

	@GetMapping("/persons/firstName")
	public ResponseEntity<Person> getPersonByNameAnd(
			@RequestParam(value = "firstName", required = true) String firstName,
			@RequestParam(value = "lastName", required = true) String lastName) {
		Person person = personService.getPersonByNameAnd(firstName, lastName);
		return ResponseEntity.ok(person);

	}

	@GetMapping("/persons/Name")
	public ResponseEntity<Person> getPersonByNameOr(
			@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "lastName") String lastName) {
		Person person = personService.getPersonByNameOr(firstName, lastName);
		return ResponseEntity.ok(person);

	}

}
