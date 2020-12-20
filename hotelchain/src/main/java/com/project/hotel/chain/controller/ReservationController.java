package com.project.hotel.chain.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.hotel.chain.model.Reservation;
import com.project.hotel.chain.service.ReservationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {

	private ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		super();
		this.reservationService = reservationService;
	}

	@ApiOperation(value = "Get all Reservations")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Reservation>> getReservations() {
		List<Reservation> reservations = reservationService.findAll();
		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}

	@ApiOperation(value = "Get reservation by Id")
		@ApiResponses({ @ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found") })
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Reservation> getReservation(@PathVariable(value = "id") Long id) {
		Reservation reservation = reservationService.findById(id);
		if (reservation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(reservation, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Get all reservations by Guest")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	
	@GetMapping(value = "/guest/{guest_id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Reservation>> getReservationByGuest(@PathVariable(value = "guest_id") Long id) {
		List<Reservation> reservas = reservationService.findByGuest(id);
		return new ResponseEntity<>(reservas, HttpStatus.OK);

	}

	@ApiOperation(value = "Get all reservations by hotel")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	
	@GetMapping(value = "/hotel/{hotel_id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Reservation>> getReservationByHotel(@PathVariable(value="hotel_id")Long id) {
		List<Reservation> reservas = reservationService.findByHotel(id);
		return new ResponseEntity<>(reservas, HttpStatus.OK);
	}

	@ApiOperation(value = "Create reservation")
	@ApiResponses({ 
		@ApiResponse(code = 201, message = "Created"), 
		@ApiResponse(code = 400, message = "Bad Request") 
	})
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
		reservation = reservationService.save(reservation);
		if (reservation == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(reservation, HttpStatus.CREATED);
		}
	}

	@ApiOperation(value = "Update reservation")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "OK"), 
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found") 
	})
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Reservation> updateReservation(@PathVariable(value = "id") Long id,
			@RequestBody Reservation reservation) {
		reservation.setId(id);
		reservation = reservationService.update(reservation);
		if (reservation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(reservation, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Delete reservation")
	@ApiResponses({ @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request") })
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteReservation(@PathVariable(value = "id") Long id) {
		reservationService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Delete all reservations")
	@ApiResponses({ @ApiResponse(code = 204, message = "No Content") })
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteReservations() {
		reservationService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
