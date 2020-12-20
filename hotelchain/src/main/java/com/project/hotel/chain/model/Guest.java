package com.project.hotel.chain.model;

import java.time.*;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "guest")
public class Guest {

	@Override
	public String toString() {
		return "Guest [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", telephone="
				+ telephone + ", birthday=" + birthday + ", profile=" + profile + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable = false)
	private String name;
	
	@Column (nullable = false)
	private String email;
	
	@Column (nullable = false)
	private String password;
	
	@Column (nullable = false)
	private Long telephone;
	
	@Column (nullable = false, columnDefinition = "Date")
	private LocalDate birthday;
	
	@Enumerated(EnumType.STRING)
	@Column (nullable = false)
	private Profile profile;

	@OneToMany(mappedBy = "guest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List <Reservation> reservation;
	
	
	public Guest() {
		super();
	}

	public Guest(Long id, String name, String email, String password, Long telephone, LocalDate birthday, Profile profile) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.telephone = telephone;
		this.birthday = birthday;
		this.profile = profile;
	}

	public enum Profile {
		FIDELITY, REGULAR
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getTelephone() {
		return telephone;
	}

	public void setTelephone(Long telephone) {
		this.telephone = telephone;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	public boolean isRegular() {
		if (getProfile() == Profile.REGULAR) {
			return true;
		} return false;
	}
	public boolean isFidelity() {
		if (getProfile() == Profile.FIDELITY) {
			return true;
		} return false;
	}

}
