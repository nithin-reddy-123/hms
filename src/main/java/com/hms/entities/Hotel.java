package com.hms.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Hotel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String hotelName;
	private String userFirstName;
	private String userLastName;
	private String email;
	@OneToMany(mappedBy="hotel", cascade = CascadeType.ALL)
	private List<Counter>counters;
	@OneToMany(mappedBy="hotel", cascade = CascadeType.ALL)
	private List<Starter>starters;
	@OneToMany(mappedBy="hotel", cascade = CascadeType.ALL)
	private List<Maincourse>maincourses;
	@OneToMany(mappedBy="hotel", cascade = CascadeType.ALL)
	private List<Dessert>desserts;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Counter> getCounters() {
		return counters;
	}
	public void setCounters(List<Counter> counters) {
		this.counters = counters;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public List<Starter> getStarters() { return starters; }
	public void setStarters(List<Starter> starters) { this.starters = starters; }
	public List<Maincourse> getMaincourses() { return maincourses; }
	public void setMaincourses(List<Maincourse> maincourses) { this.maincourses = maincourses; }
	public List<Dessert> getDesserts() { return desserts; }
	public void setDesserts(List<Dessert> desserts) { this.desserts = desserts; }
}
