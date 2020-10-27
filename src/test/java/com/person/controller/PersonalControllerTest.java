package com.person.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.person.exception.ResourceNotFoundException;
import com.person.model.Person;
import com.person.service.PersonService;

class PersonalControllerTest {

	@InjectMocks
	PersonController personController;

	@Mock
	PersonService personService;
	Person personSample;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		personSample = new Person();
		personSample.setCurrentAddress("bangalore");
		personSample.setLastName("kalap");
		personSample.setFirstName("sarar");
		personSample.setDateOfBirth(LocalDate.now());

	}

	@Test
	void testGetPersonById() throws ResourceNotFoundException {
		when(personService.getPersonById(anyLong())).thenReturn(personSample);
		Person person = personController.getPersonById(1l);
		assertNotNull(person);
		assertEquals("sarar", person.getFirstName());
		assertEquals("kalap", person.getLastName());
		assertEquals("bangalore", person.getCurrentAddress());
	}

	@Test
	void testGetAllPersons() throws ResourceNotFoundException {
		Person personSample = new Person();
		personSample.setCurrentAddress("bangalore");
		personSample.setLastName("kalap");
		personSample.setFirstName("sarar");
		personSample.setDateOfBirth(LocalDate.now());
		List<Person> listPerson = new ArrayList<Person>();

		when(personService.getAllPersons()).thenReturn(listPerson);
		ResponseEntity<List<Person>> personList = personController.getAllPersons();
		assertNotNull(personList);
		assertEquals("200 OK", personList.getStatusCode().toString());
		assertEquals(listPerson, personList.getBody());

	}

	@Test
	void testUpdatePerson() throws ResourceNotFoundException {
		when(personService.updatePerson(anyLong(), any(Person.class))).thenReturn(personSample);
		ResponseEntity<Person> person = personController.updatePerson(1l, personSample);
		assertNotNull(person);
		assertEquals(personSample, person.getBody());
		assertEquals(200, person.getStatusCodeValue());

	}

	@Test
	void testCreatePerson() throws ResourceNotFoundException {
		when(personService.createPerson(any(Person.class))).thenReturn(personSample);
		ResponseEntity<Person> person = personController.createPerson(personSample);
		assertNotNull(person);
		assertEquals(personSample, person.getBody());
		assertEquals(200, person.getStatusCodeValue());

	}

	@Test
	void testGetPersonByNameAnd() throws ResourceNotFoundException {
		when(personService.getPersonByNameAnd(anyString(), anyString())).thenReturn(personSample);
		ResponseEntity<Person> person = personController.getPersonByNameAnd("sarar", "kalap");
		assertNotNull(person);
		assertEquals("sarar", person.getBody().getFirstName());
		assertEquals("kalap", person.getBody().getLastName());
		assertEquals("bangalore", person.getBody().getCurrentAddress());
		assertEquals(200, person.getStatusCodeValue());
	}

	@Test
	void testGetPersonByNameOr() throws ResourceNotFoundException {
		when(personService.getPersonByNameOr(anyString(), anyString())).thenReturn(personSample);
		ResponseEntity<Person> person = personController.getPersonByNameOr("sarar", "kalap");
		assertNotNull(person);
		assertEquals("sarar", person.getBody().getFirstName());
		assertEquals("kalap", person.getBody().getLastName());
		assertEquals("bangalore", person.getBody().getCurrentAddress());
		assertEquals(200, person.getStatusCodeValue());
	}

	@Test
	final void testDeletePerson() throws ResourceNotFoundException {

		Map<String, Boolean> response = new HashMap<String,Boolean>();
		response.put("deleted", Boolean.TRUE);
		when(personService.deletePerson(anyLong())).thenReturn(response);
		Map<String, Boolean> res = personController.deletePerson(1l);
		assertEquals(response, res);
	}
}
