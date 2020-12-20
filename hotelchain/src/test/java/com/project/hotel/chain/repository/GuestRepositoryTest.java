package com.project.hotel.chain.repository;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.hotel.chain.model.*;
import com.project.hotel.chain.model.Guest.Profile;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations="classpath:application.properties")

public class GuestRepositoryTest {
	
	@Autowired
	private GuestRepository guestRepository;
	
	@Test
	public void findAll() {
		
		List<Guest> guests = guestRepository.findAll();
		
		guests.stream().forEach(System.out::println);
	}
	
	@Test
	public void findById() {
		Long id =1L;
		Guest guest = guestRepository.findById(id).orElse(null);
		System.out.println(guest);
	}
	
	@Test
	public void findByNameContainingIgnoreCase() {
		String name = "guest";
		List <Guest> guests = guestRepository.findByNameContainingIgnoreCase(name);
		guests.stream().forEach(System.out::println);
	}
		
	@Test
	public void save_create() {
		Guest guest = new Guest();
		guest.setId(1L);
		guest.setName("gueste");
		guest.setEmail("gueste@gmial.com");
		guest.setPassword("1234");
		guest.setTelephone(8L);
		guest.setBirthday(LocalDate.parse("1969-10-11"));
		guest.setProfile(Profile.REGULAR);
		guest = guestRepository.save(guest);
		System.out.println(guest);
	}
	
	@Test
	public void save_update() {
		Guest guest = new Guest();
		guest.setId(1L);
		guest.setName("guesty");
		guest.setEmail("guesty@gmial.com");
		guest.setPassword("1235");
		guest.setTelephone(7L);
		guest.setBirthday(LocalDate.parse("1970-10-11"));
		guest.setProfile(Profile.FIDELITY);
		guest = guestRepository.save(guest);
		System.out.println(guest);
	}
	
	@Test
	public void deleteById() {
		Long id = 1L;
		guestRepository.deleteById(id);
	}
	
	@Test
	public void deleteAll() {
		guestRepository.deleteAllInBatch();
	}


}
