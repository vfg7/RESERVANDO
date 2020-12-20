package com.project.hotel.chain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hotel.chain.model.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
	
	List<Guest> findByNameContainingIgnoreCase(String name);

}
