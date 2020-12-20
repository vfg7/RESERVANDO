package com.project.hotel.chain.model;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "reservation")
public class Reservation {

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", checkin=" + checkin + ", checkout=" + checkout + ", value=" + value
				+ ", hotel=" + hotel.getName() + ", guest=" + guest.getName() + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable = false, columnDefinition = "Date")
	private LocalDate checkin;
	
	@Column (nullable = false, columnDefinition = "Date")
	private LocalDate checkout;
	
	@Column (nullable = false)
	private Long value;

	@JsonIgnoreProperties({"reservation"})
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private Hotel hotel;
	
	@JsonIgnoreProperties({"reservation"})
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private Guest guest;

	public Reservation() {
		super();
	}

	public Reservation(Long id, LocalDate checkin, LocalDate checkout) {
		super();
		this.id = id;
		this.checkin = checkin;
		this.checkout = checkout;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getCheckin() {
		return checkin;
	}

	public void setCheckin(LocalDate checkin) {
		this.checkin = checkin;
	}

	public LocalDate getCheckout() {
		return checkout;
	}

	public void setCheckout(LocalDate checkout) {
		this.checkout = checkout;
	}

	public Long getValue() {
		calculateValue();
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
	public void setValue() {
		calculateValue();
	}
	public Hotel getHotel() {
		return hotel;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	
	
	public void calculateValue() {
		LocalDate date = this.checkin; 
		LocalDate date2 = this.checkout; 
		
		if(date2.isBefore(date)) {
			System.out.println("Checkout antes do checkin!");
			return;
		}
		
		long stay = ChronoUnit.DAYS.between(date, date2);
		long valor = 0L;
		long weekday=0L;
		long weekend =0L;
		
		if(this.guest.isRegular()) {
			weekday = hotel.getWeekdayRegular();
			weekend = hotel.getWeekendRegular();
		} else if (this.guest.isFidelity()) {
			weekday = hotel.getWeekdayFidelity();
			weekend = hotel.getWeekendFidelity();
		}
		
		for (int i=0;i<stay;i++) {
			DayOfWeek dow = DayOfWeek.from(date);
			if (dow.toString().equals("SATURDAY")||dow.toString().equals("SUNDAY")) {
				valor += weekend;
				
			} else {
				valor += weekday;				
			}
			date = date.plusDays(1);
			
		}
		
		this.value=valor;
		
	}


}
