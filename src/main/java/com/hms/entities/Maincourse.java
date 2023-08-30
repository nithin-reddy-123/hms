package com.hms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Maincourse {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String maincourseName;
	private Long maincoursePrice;
	@ManyToOne
	@JoinColumn(name="hotel_id")
	private Hotel hotel;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMaincourseName() {
		return maincourseName;
	}
	public void setMaincourseName(String mainCourseName) {
		this.maincourseName = mainCourseName;
	}
	public Long getMaincoursePrice() {
		return maincoursePrice;
	}
	public void setMaincoursePrice(Long mainCoursePrice) {
		this.maincoursePrice = mainCoursePrice;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
}
