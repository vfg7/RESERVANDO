package com.project.hotel.chain.service;

import org.springframework.stereotype.Service;

import com.project.hotel.chain.model.*;

import com.project.hotel.chain.repository.ReservationRepository;

import java.util.*;

@Service
public class ReservationService {

	private ReservationRepository reservationRepository;
	private HotelService hotelService;
	private GuestService guestService;

	public ReservationService(ReservationRepository reservationRepository, HotelService hotelService,
			GuestService guestService) {
		super();
		this.reservationRepository = reservationRepository;
		this.guestService = guestService;
		this.hotelService = hotelService;
	}

	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	public Reservation findById(Long id) {

		return reservationRepository.findById(id).orElse(null);

	}

	public List<Reservation> findByHotel(Long Id) {
		Hotel hotel = new Hotel();
		hotel.setId(Id);
		if (reservationRepository.findByHotel(hotel) != null) {
			return reservationRepository.findByHotel(hotel);
		}

		return null;

	}

	public List<Reservation> findByGuest(Long id) {
		Guest guest = new Guest();
		guest.setId(id);
		if (reservationRepository.findByGuest(guest) != null) {
			return reservationRepository.findByGuest(guest);
		}

		return null;

	}

	public Reservation save(Reservation reservation) {
		reservation.setId(null);
		return internalSave(reservation);

	}

	public Reservation update(Reservation reservation) {
		if (reservation.getId() != null) {
			if (reservationRepository.existsById(reservation.getId())) {
				return internalSave(reservation);
			}
		}

		return null;

	}

	public void deleteById(Long id) {
		if (reservationRepository.existsById(id)) {
			reservationRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		reservationRepository.deleteAllInBatch();
	}

	private Reservation internalSave(Reservation reservation) {
		try {
			
			reservation = reservationRepository.save(reservation);
			
			if (reservation != null) {
				Hotel hotel = reservation.getHotel();
				hotel = hotelService.findById(hotel.getId());
				reservation.setHotel(hotel);

				Guest guest = reservation.getGuest();
				guest = guestService.findById(guest.getId());
				reservation.setGuest(guest);
			}
			return reservation;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
