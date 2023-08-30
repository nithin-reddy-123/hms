package com.hms.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.entities.Maincourse;
import com.hms.repositories.MaincourseRepository;
import com.hms.service.MaincourseService;

@Service
public class MaincourseServiceImpl implements MaincourseService{

	@Autowired
	private MaincourseRepository maincourseRepository; 
	
	@Override
	public Maincourse getMaincourseById(Long id) {
		return maincourseRepository.findById(id).get();
	}

	@Override
	public Maincourse updateMaincourse(Maincourse maincourse) {
		return maincourseRepository.save(maincourse);
	}

	@Override
	public Maincourse saveMaincourse(Maincourse maincourse) {
		return maincourseRepository.save(maincourse);
	}

	@Override
	public void deleteMaincourseById(Long id) {
		maincourseRepository.deleteById(id);
	}

	@Override
	public List<Maincourse> getMaincoursesByHotelId(Long hotelId) {
		return maincourseRepository.findAllByHotelId(hotelId);
	}

}
