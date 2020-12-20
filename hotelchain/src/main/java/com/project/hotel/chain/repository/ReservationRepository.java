package com.project.hotel.chain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.hotel.chain.model.*;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	
	List <Reservation> findByGuest(Guest guest);
	List <Reservation> findByHotel (Hotel hotel);
	

}
