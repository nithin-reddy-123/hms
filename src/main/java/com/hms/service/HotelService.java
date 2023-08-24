package com.hms.service;

import java.util.List;

import com.hms.entities.Counter;
import com.hms.entities.Hotel;

public interface HotelService {
	
	public List<Hotel>getAllHotels();
	Hotel getHotelById(Long id);
	Hotel updateHotel(Hotel hotel);
	Hotel saveHotel(Hotel hotel);
	List<Counter> getCountersById(Long id);
	void deleteHotelById(Long id);
	List<Hotel> getHotelByHotelName(String hotelName);


	Long findHotelIdByHotelName(String hotelName);
	

}
