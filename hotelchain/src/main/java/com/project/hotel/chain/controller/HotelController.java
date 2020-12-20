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

import com.project.hotel.chain.model.*;
import com.project.hotel.chain.service.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/hotel", produces = MediaType.APPLICATION_JSON_VALUE)
public class HotelController {
	
	private HotelService hotelService;
	
	public HotelController(HotelService hotelService) {
		super();
		this.hotelService = hotelService;
	}
	
	@ApiOperation(value = "Get all Hotels")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
	})
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Hotel>> getHotels(@RequestParam(name = "name", required = false) String name) {
		List<Hotel> hotels = null;
		if (name==null) { 
			hotels = hotelService.findAll();
		} else {
			hotels = hotelService.findByNameContainingIgnoreCase(name);
		}
		return new ResponseEntity<>(hotels, HttpStatus.OK);
	}

	@ApiOperation(value = "Get hotel")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Hotel> getHotel(@PathVariable(value = "id") Long id) {
		Hotel hotel = hotelService.findById(id);
		if (hotel == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(hotel, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Create hotel")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Created"),
		@ApiResponse(code = 400, message = "Bad Request")
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
		hotel = hotelService.save(hotel);
		if (hotel == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(hotel, HttpStatus.CREATED);
		}
	}

	@ApiOperation(value = "Update hotel")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Hotel> updateHotel(@PathVariable(value = "id") Long id, @RequestBody Hotel hotel) {
		hotel.setId(id);
		hotel = hotelService.update(hotel);
		if (hotel == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(hotel, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Delete hotel")
	@ApiResponses({
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "Bad Request")
	})
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteHotel(@PathVariable(value = "id") Long id) {
		hotelService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Delete all hotels")
	@ApiResponses({
		@ApiResponse(code = 204, message = "No Content")
	})
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteHotels() {
		hotelService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
