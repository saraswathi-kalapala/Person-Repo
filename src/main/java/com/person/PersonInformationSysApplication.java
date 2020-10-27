package com.person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = { "com.person.*" })
public class PersonInformationSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonInformationSysApplication.class, args);
	}

}
