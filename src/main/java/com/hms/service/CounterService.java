package com.hms.service;

import java.util.List;

import com.hms.entities.Counter;


public interface CounterService {
	
	public List<Counter>getAllCounters();
	Counter getCounterById(Long id);
	Counter updateCounter(Counter counter);
	Counter saveCounter(Counter counter);
	void deleteCounterById(Long id);
	List<Counter> getCountersByHotelId(Long hotelId);

	boolean existsCounterByNumber(Long counterNumber);
}
