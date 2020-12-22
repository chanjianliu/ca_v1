package com.team9.motors.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.team9.motors.repository.UserRepository;

@SpringBootTest
public class Mailtest {
	@Autowired
	private UserRepository urepo;

	@Test
	void getmail() {
		urepo.findAll();
	}

}
