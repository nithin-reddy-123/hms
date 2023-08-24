package com.hms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hms.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long>{
	
	List<Hotel> findAllByHotelName(String hotelName);
	
	@Query("SELECT h.id FROM Hotel h WHERE h.hotelName = :hotelName")
	Long findHotelIdByHotelName(@Param("hotelName") String hotelName);
	Hotel findHotelById(Long hid);
}
