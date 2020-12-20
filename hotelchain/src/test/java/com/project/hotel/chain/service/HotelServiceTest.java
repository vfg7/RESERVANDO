package com.project.hotel.chain.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.hotel.chain.model.Hotel;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class HotelServiceTest {
	
	@Autowired
	HotelService hotelService;

	@Test
	public void findAll() {
		List<Hotel> hotels = hotelService.findAll();
		for (Hotel hotel : hotels) {
			System.out.println(hotel);
		}

	}

	public void findById() {
		Long id = 1L;
		Hotel hotel = hotelService.findById(id);
		System.out.println(hotel.toString());

	}

	public void findByNameContainingIgnoreCase() {

		String name = "name";
		List<Hotel> hotels = hotelService.findByNameContainingIgnoreCase(name);
		hotels.forEach(System.out::println);

	}

	public void save() {
		Hotel hotel = new Hotel();
		hotel.setId(null);
		hotel.setName("nome");

		hotelService.save(hotel);

		System.out.println(hotel.toString());

	}

	public void update() {
		Hotel hotel = new Hotel();
		hotel.setId(2L);
		hotel.setName("name");

		hotelService.save(hotel);

		System.out.println(hotel.toString());
	}

	public void deleteById() {
		Long id = 1L;
		hotelService.deleteById(id);
	}

	public void deleteAll() {
		hotelService.deleteAll();
	}


}
