package com.project.hotel.chain.service;

import org.springframework.stereotype.Service;

import com.project.hotel.chain.model.*;
import com.project.hotel.chain.repository.*;

import java.util.*;

@Service
public class HotelService {
	
	private HotelRepository hotelRepository;

	public HotelService(HotelRepository hotelRepository) {
		super();
		this.hotelRepository = hotelRepository;
	}

	public List<Hotel> findAll() {
		return hotelRepository.findAll();
	}
	
	public Hotel findById(Long id) {
		
		return hotelRepository.findById(id).orElse(null);
		
	}
	
	
	public List<Hotel> findByNameContainingIgnoreCase(String name) {
		
		return hotelRepository.findByNameContainingIgnoreCase(name);
		
	}
		
	
	public Hotel save(Hotel hotel) {
		hotel.setId(null);
		return internalSave(hotel);
		
	}
	
	
	public Hotel update(Hotel hotel) {
		
		if (hotel.getId()!=null) {
			if(hotelRepository.existsById(hotel.getId())) {
				return internalSave(hotel);
			}
		}
		
		return null;
	}
	
	
	public void deleteById(Long id) {
		if(hotelRepository.existsById(id)) {
			hotelRepository.deleteById(id);
		}
	}
	
	
	public void deleteAll() {
		hotelRepository.deleteAllInBatch();
	}
	
	private Hotel internalSave(Hotel hotel) {
		try {
			return hotelRepository.save(hotel);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	

}
