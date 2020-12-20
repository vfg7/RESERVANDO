package com.project.hotel.chain.service;

import org.springframework.stereotype.Service;

import com.project.hotel.chain.model.*;
import com.project.hotel.chain.repository.*;

import java.util.*;

@Service
public class GuestService {

	private GuestRepository guestRepository;

	public GuestService(GuestRepository guestRepository) {
		super();
		this.guestRepository = guestRepository;
	}

	public List<Guest> findAll() {
		return guestRepository.findAll();
	}

	public Guest findById(Long id) {

		return guestRepository.findById(id).orElse(null);

	}

	public List<Guest> findByNameContainingIgnoreCase(String name) {

		return guestRepository.findByNameContainingIgnoreCase(name);

	}

	public Guest save(Guest guest) {
		guest.setId(null);
		return internalSave(guest);

	}

	public Guest update(Guest guest) {
		if (guest.getId()!=null) {
			if(guestRepository.existsById(guest.getId())) {
				return internalSave(guest);
			}
		}
		
		return null;
	}

	public void deleteById(Long id) {
		if (guestRepository.existsById(id)) {
			guestRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		guestRepository.deleteAllInBatch();
	}

	private Guest internalSave(Guest guest) {
		try {
			return guestRepository.save(guest);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
