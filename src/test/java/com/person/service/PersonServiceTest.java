package com.person.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.person.exception.ResourceNotFoundException;
import com.person.model.Person;
import com.person.repository.PersonRepository;
 

class PersonServiceTest {

	@InjectMocks
	PersonService personService;
	@Mock
	PersonRepository personRepository;
	Person personSample;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		personSample = new Person();
		personSample = new Person();
		personSample.setCurrentAddress("bangalore");
		personSample.setLastName("kalap");
		personSample.setFirstName("sarar");
		personSample.setDateOfBirth(LocalDate.now());
	}

	@Test
	final void testGetPersonById() throws ResourceNotFoundException {

		Optional<Person> p = Optional.of(personSample);
		when(personRepository.findById(anyLong())).thenReturn(p);
		Person person = personService.getPersonById(1l);
		assertNotNull(person);
		assertEquals("sarar", person.getFirstName());

	}

	@Test
	final void testGetPersonIdNotFound() throws ResourceNotFoundException {

		when(personRepository.findById(anyLong())).thenReturn(null);

		assertThrows(ResourceNotFoundException.class, () -> {

			personService.getPersonById(null);
		});
	}

	@Test
	final void testCreatePerson() {

		when(personRepository.save(any(Person.class))).thenReturn(personSample);
		Person person = personService.createPerson(personSample);
		assertEquals("sarar", person.getFirstName());

	}

	@Test
	final void testGetAllPersons() {
		Person personSample = new Person();
		personSample.setCurrentAddress("bangalore");
		personSample.setLastName("kalap");
		personSample.setFirstName("sarar");
		personSample.setId(1L);
		personSample.setDateOfBirth(LocalDate.now());
		List<Person> listPersons = new ArrayList<Person>();

		listPersons.add(personSample);
		when(personRepository.findAll()).thenReturn(listPersons);
		List<Person> personList = personService.getAllPersons();
		assertNotNull(personList);
		assertEquals("sarar", personList.get(0).getFirstName());

	}

	@Test
	final void testUpdatePerson() throws ResourceNotFoundException {

		Optional<Person> p = Optional.of(personSample);

		personSample.setDateOfBirth(LocalDate.now());
		when(personRepository.findById(anyLong())).thenReturn(p);
		when(personRepository.save(any(Person.class))).thenReturn(personSample);
		Person person = personService.updatePerson(1l, personSample);

		assertNotNull(person);
		assertEquals("sarar", person.getFirstName());
	}

	@Test
	final void testUpdatePersonIdNotFound() throws ResourceNotFoundException {

		when(personRepository.findById(anyLong())).thenReturn(null);

		assertThrows(ResourceNotFoundException.class, () -> {

			personService.updatePerson(null, null);
		});
	}

	@Test
	final void testGetPersonNameAnd() throws ResourceNotFoundException {

		when(personRepository.findByFirstNameAndLastName(anyString(), anyString())).thenReturn(personSample);
		Person person = personService.getPersonByNameAnd("sarar", "kalap");
		assertNotNull(person);
		assertEquals("sarar", person.getFirstName());

	}

	@Test
	final void testGetPersonNameOr() throws ResourceNotFoundException {

		when(personRepository.findByFirstNameOrLastName(anyString(), anyString())).thenReturn(personSample);
		Person person = personService.getPersonByNameOr("sarar", "kalap");
		assertNotNull(person);
		assertEquals("sarar", person.getFirstName());

	}

	@Test
	final void testDeletePerson() throws ResourceNotFoundException {

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		Person personSample = new Person();
		personSample.setCurrentAddress("bangalore");
		personSample.setLastName("kalap");
		personSample.setFirstName("sarar");
		personSample.setDateOfBirth(LocalDate.now());
		Optional<Person> p = Optional.of(personSample);
		when(personRepository.findById(anyLong())).thenReturn(p);
		personRepository.delete(p.get());
		Map<String, Boolean> res = personService.deletePerson(1l);
		assertEquals(response, res);
	}

	@Test
	final void testToDeleteIdNotFound() throws ResourceNotFoundException {

		when(personRepository.findById(anyLong())).thenReturn(null);

		assertThrows(ResourceNotFoundException.class, () -> {

			personService.deletePerson(null);
		});
	}

	@AfterEach
	void tearDown() {
		personSample = null;
	}
}
