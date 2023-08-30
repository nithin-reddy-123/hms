package com.hms.service;

import java.util.List;

import com.hms.entities.Maincourse;

public interface MaincourseService {
	
	Maincourse getMaincourseById(Long id);
	Maincourse updateMaincourse(Maincourse maincourse);
	Maincourse saveMaincourse(Maincourse maincourse);
	void deleteMaincourseById(Long id);
	List<Maincourse> getMaincoursesByHotelId(Long hotelId);
}
