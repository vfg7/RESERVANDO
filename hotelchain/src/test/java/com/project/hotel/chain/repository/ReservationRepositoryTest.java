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


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations="classpath:application.properties")

public class ReservationRepositoryTest {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Test
	public void findAll() {
		
		List<Reservation> reservations = reservationRepository.findAll();
		
		reservations.stream().forEach(System.out::println);
	}
	
	@Test
	public void findById() {
		Long id =1L;
		Reservation reservation = reservationRepository.findById(id).orElse(null);
		System.out.println(reservation);
	}
	
	@Test
	public void findByHotel() {
		Hotel hotel = new Hotel();
		List <Reservation> reservations = reservationRepository.findByHotel(hotel);
		reservations.stream().forEach(System.out::println);
	}
	
	@Test
	public void findByGuest() {
		Guest guest = new Guest();
		List <Reservation> reservations = reservationRepository.findByGuest(guest);
		reservations.stream().forEach(System.out::println);
	}
		
	@Test
	public void save_create() {
		Hotel hotel = new Hotel();
		hotel.setId(1L);
		
		Guest guest = new Guest();
		guest.setId(1L);
		
		Reservation reservation = new Reservation();
		reservation.setId(1L);
		reservation.setCheckin(LocalDate.parse("2020-01-01"));
		reservation.setCheckout(LocalDate.parse("2020-12-31"));
		reservation.setGuest(guest);
		reservation.setHotel(hotel);
		reservation.setValue();
		reservation = reservationRepository.save(reservation);
		System.out.println(reservation);
	}
	
	@Test
	public void save_update() {
		Hotel hotel = new Hotel();
		hotel.setId(1L);
		
		Guest guest = new Guest();
		guest.setId(1L);
		
		Reservation reservation = new Reservation();
		reservation.setId(null);
		reservation.setCheckin(LocalDate.parse("2020-02-01"));
		reservation.setCheckout(LocalDate.parse("2020-11-31"));
		reservation.setGuest(guest);
		reservation.setHotel(hotel);
		reservation.setValue();
		reservation = reservationRepository.save(reservation);
		System.out.println(reservation);
	}
	
	@Test
	public void deleteById() {
		Long id = 1L;
		reservationRepository.deleteById(id);
	}
	
	@Test
	public void deleteAll() {
		reservationRepository.deleteAllInBatch();
	}


}
