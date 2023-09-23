package com.hms.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.entities.Counter;
import com.hms.entities.Hotel;
import com.hms.repositories.HotelRepository;
import com.hms.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public List<Hotel> getAllHotels() {
		
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotelById(Long id) {

		return hotelRepository.findById(id).get();
	}

	@Override
	public Hotel updateHotel(Hotel hotel) {

		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel saveHotel(Hotel hotel) {

		return hotelRepository.save(hotel);
	}

	@Override
	public void deleteHotelById(Long id) {

		hotelRepository.deleteById(id);
		
	}

	@Override
	public List<Counter> getCountersById(Long id) {
		
		Hotel hotel=hotelRepository.findById(id).get();
		List<Counter>counters=hotel.getCounters();
		counters.forEach(x->System.out.println(x));
		return counters;
	}

	@Override
	public List<Hotel> getHotelByHotelName(String hotelName) {
		List<Hotel>hotels=hotelRepository.findAllByHotelName(hotelName);
		return hotels;
	}

	@Override
	public Long findHotelIdByHotelName(String hotelName) {
		return hotelRepository.findHotelIdByHotelName(hotelName);
	}

	
}
