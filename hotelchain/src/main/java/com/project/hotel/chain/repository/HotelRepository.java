package com.project.hotel.chain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hotel.chain.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
	
	List <Hotel> findByNameContainingIgnoreCase(String name);

}
