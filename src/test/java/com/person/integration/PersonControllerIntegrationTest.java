package com.person.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.HttpClientErrorException;

import com.person.PersonInformationSysApplication;
import com.person.model.Person;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = PersonInformationSysApplication.class)
@AutoConfigureWebMvc

public class PersonControllerIntegrationTest {

	@Autowired
	TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Sql({ "classpath:schema.sql", "classpath:data.sql" })

	@Test
	public void testGetAllPersons() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(getRootUrl() + "/requirement/v1/persons", HttpMethod.GET, entity,
				String.class);

		System.out.println(result);
		assertNotNull(result.getBody());
	}

	@Sql({ "classpath:schema.sql", "classpath:data.sql" })
	@Test
	public void testGetAllPersons1() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/requirement/v1/persons", HttpMethod.GET,
				entity, String.class);
		assertNotNull(response.getBody());
	}

	@Sql({ "classpath:schema.sql", "classpath:data.sql" })

	@Test
	public void testGetPersonById() {
		Person person = restTemplate.getForObject(getRootUrl() + "/requirement/v1/persons/1l", Person.class);
		System.out.println(person.getFirstName());
		assertNotNull(person);
	}

	@Test
	public void testCreateEmployee() {
		Person personSample = new Person();
		personSample = new Person();
		personSample.setCurrentAddress("bangalore");
		personSample.setLastName("kalap");
		personSample.setFirstName("sarar");
		personSample.setDateOfBirth(LocalDate.now());
		ResponseEntity<Person> postResponse = restTemplate.postForEntity(getRootUrl() + "/requirement/v1/persons", personSample,
				Person.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateEmployee() {
		long id = 1l;
		Person person = restTemplate.getForObject(getRootUrl() + "/requirement/v1/persons/" + id, Person.class);
		person.setCurrentAddress("bangalore");
		person.setLastName("kalap");
		person.setFirstName("sarar");
		person.setDateOfBirth(LocalDate.now());
		restTemplate.put(getRootUrl() + "/api/v1/persons/" + id, person);
		Person updatedPerson = restTemplate.getForObject(getRootUrl() + "/requirement/v1/persons/" + id, Person.class);
		assertNotNull(updatedPerson);
	}

	@Test
	public void testDeleteEmployee() {
		long id = 1l;
		Person person = restTemplate.getForObject(getRootUrl() + "/requirement/v1/persons/" + id, Person.class);
		assertNotNull(person);
		restTemplate.delete(getRootUrl() + "/api/v1/persons/" + id);
		try {
			person = restTemplate.getForObject(getRootUrl() + "/requirement/v1/persons/" + id, Person.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

	@Sql({ "classpath:schema.sql", "classpath:data.sql" })

	@Test
	public void testGetPersonByNameAnd() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("firstName", "sarar");
		params.put("lastName", "kalap");

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(getRootUrl() + "/requirement/v1/persons", HttpMethod.GET, entity,
				String.class, params);

		System.out.println(result);
		assertNotNull(result.getBody());
	}

	@Sql({ "classpath:schema.sql", "classpath:data.sql" })

	@Test
	public void testGetPersonByNameOr() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("firstName", "sarar");
		params.put("lastName", "kalap");

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(getRootUrl() + "/requirement/v1/persons", HttpMethod.GET, entity,
				String.class, params);

		System.out.println(result);
		assertNotNull(result.getBody());
	}

}