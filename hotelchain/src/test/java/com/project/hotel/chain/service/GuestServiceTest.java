package com.project.hotel.chain.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.hotel.chain.model.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class GuestServiceTest {

	@Autowired
	GuestService guestService;

	@Test
	public void findAll() {
		List<Guest> guests = guestService.findAll();
		for (Guest guest : guests) {
			System.out.println(guest);
		}

	}

	public void findById() {
		Long id = 1L;
		Guest guest = guestService.findById(id);
		System.out.println(guest.toString());

	}

	public void findByNameContainingIgnoreCase() {

		String name = "name";
		List<Guest> guests = guestService.findByNameContainingIgnoreCase(name);
		guests.forEach(System.out::println);

	}

	public void save() {
		Guest guest = new Guest();
		guest.setId(null);
		guest.setName("nome");

		guestService.save(guest);

		System.out.println(guest.toString());

	}

	public void update() {
		Guest guest = new Guest();
		guest.setId(2L);
		guest.setName("name");

		guestService.save(guest);

		System.out.println(guest.toString());
	}

	public void deleteById() {
		Long id = 1L;
		guestService.deleteById(id);
	}

	public void deleteAll() {
		guestService.deleteAll();
	}


}
