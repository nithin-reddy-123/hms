package com.hms.service;

import java.util.List;

import com.hms.entities.Counter;
import com.hms.entities.Hotel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface HotelService extends UserDetailsService {
	
	public List<Hotel>getAllHotels();
	Hotel getHotelById(Long id);
	Hotel updateHotel(Hotel hotel);
	Hotel saveHotel(Hotel hotel);
	List<Counter> getCountersById(Long id);
	void deleteHotelById(Long id);
	List<Hotel> getHotelByHotelName(String hotelName);

	Long findHotelIdByHotelName(String hotelName);

	Hotel findHotelByEmail(String emailAddress);

}
