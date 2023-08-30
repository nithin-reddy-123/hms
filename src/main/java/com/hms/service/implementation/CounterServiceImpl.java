package com.hms.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.entities.Counter;
import com.hms.repositories.CounterRepository;
import com.hms.service.CounterService;

@Service
public class CounterServiceImpl implements CounterService{
	
	@Autowired
	private CounterRepository counterRepository;
	
	public CounterServiceImpl(CounterRepository counterRepository) {
		super();
		this.counterRepository = counterRepository;
	}

	@Override
	public List<Counter> getAllCounters() {
		
		return counterRepository.findAll();
	}

	@Override
	public Counter getCounterById(Long id) {
		
		return counterRepository.findById(id).get();
	}

	@Override
	public Counter updateCounter(Counter counter) {

		return counterRepository.save(counter);
	}

	@Override
	public Counter saveCounter(Counter counter) {

		return counterRepository.save(counter);
	}

	@Override
	public void deleteCounterById(Long id) {
		counterRepository.deleteById(id);	
	}

	@Override
	public List<Counter> getCountersByHotelId(Long hotelId) {
		return counterRepository.findAllByHotelId(hotelId);
	}

}
