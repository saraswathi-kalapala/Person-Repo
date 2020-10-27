package com.person.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "persons", uniqueConstraints = { @UniqueConstraint(columnNames = { "first_name", "last_name" }) })
public class Person {

	private long id;
	private String firstName;
	private String lastName;
	private String currentAddress;
	private LocalDate dateOfBirth;

	public Person() {

	}

	public Person(long id, String firstName, String lastName, String currentAddress, LocalDate dateOfBirth) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.currentAddress = currentAddress;
		this.dateOfBirth = dateOfBirth;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "current_Address", nullable = false)
	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	@Column(name = "date_of_birth", nullable = false)
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}