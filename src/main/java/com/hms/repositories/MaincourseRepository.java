package com.hms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entities.Maincourse;

public interface MaincourseRepository extends JpaRepository<Maincourse, Long>{

	List<Maincourse> findAllByHotelId(Long hotelId);
}
