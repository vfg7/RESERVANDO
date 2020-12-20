package com.project.hotel.chain.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.hotel.chain.model.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ReservationServiceTest {
	
	@Autowired
	ReservationService reservationService;
	HotelService hotelService;
	GuestService guestService;

	@Test
	public void findAll() {
		List<Reservation> reservations = reservationService.findAll();
		for (Reservation reservation : reservations) {
			System.out.println(reservation);
		}

	}

	public void findById() {
		Long id = 1L;
		Reservation reservation = reservationService.findById(id);
		System.out.println(reservation.toString());

	}
	
	public void findByHotel() {
		Long Id = 1L;
		List<Reservation> res = reservationService.findByHotel(Id);
		res.forEach(System.out::println);
	}

	public void findByGuest() {
		Long id = 1L;
		List<Reservation> res = reservationService.findByGuest(id);
		res.forEach(System.out::println);
	}

	

	public void save() {
		Reservation reservation = new Reservation();
		reservation.setId(null);

		reservationService.save(reservation);

		System.out.println(reservation.toString());

	}

	public void update() {
		Reservation reservation = new Reservation();
		reservation.setId(2L);
		

		reservationService.save(reservation);

		System.out.println(reservation.toString());
	}

	public void deleteById() {
		Long id = 1L;
		reservationService.deleteById(id);
	}

	public void deleteAll() {
		reservationService.deleteAll();
	}


}
