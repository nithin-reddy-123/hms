package com.hms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Counter {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String hotelName;
	private Integer counterNumber;
	@ManyToOne
	@JoinColumn(name="hotel_id")
	private Hotel hotel;
	
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
	public Integer getCounterNumber() {
		return counterNumber;
	}
	public void setCounterNumber(Integer counterNumber) {
		this.counterNumber = counterNumber;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public Long getHotelId() {
		return hotel.getId();
	}
	public void setHotelId(Long hotelId) {
		this.hotel.setId(hotelId);
	}
	
}
