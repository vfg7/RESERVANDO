package com.project.hotel.chain.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.hotel.chain.model.*;



@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations="classpath:application.properties")

public class HotelRepositoryTest {

	@Autowired
	private HotelRepository hotelRepository;
	
	@Test
	public void findAll() {
		
		List<Hotel> hotels = hotelRepository.findAll();
		
		hotels.stream().forEach(System.out::println);
	}
	
	@Test
	public void findById() {
		Long id =1L;
		Hotel hotel = hotelRepository.findById(id).orElse(null);
		System.out.println(hotel);
	}
	
	@Test
	public void findByNameContainingIgnoreCase() {
		String name = "hotel";
		List <Hotel> hotels = hotelRepository.findByNameContainingIgnoreCase(name);
		hotels.stream().forEach(System.out::println);
	}
		
	@Test
	public void save_create() {
		
		Hotel hotel = new Hotel(null, "hotel california","suchalovelyplace@mail.com",212L,4L,200L,300L,150L,100L);
		hotel.setId(1L);
		hotel = hotelRepository.save(hotel);
		System.out.println(hotel);
	}
	
	@Test
	public void save_update() {
		
		Hotel hotel = new Hotel(null, "hotel tihuana","sunsgone@mail.com",22L,3L,250L,250L,220L,120L);
		hotel.setId(1L);
		hotel = hotelRepository.save(hotel);
		System.out.println(hotel);
	}
	
	@Test
	public void deleteById() {
		Long id = 1L;
		hotelRepository.deleteById(id);
	}
	
	@Test
	public void deleteAll() {
		hotelRepository.deleteAllInBatch();
	}
	
}
