package com.hms.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.entities.Starter;
import com.hms.repositories.StarterRepository;
import com.hms.service.StarterService;

@Service
public class StarterServiceImpl implements StarterService{

	@Autowired
	private StarterRepository starterRepository;
	
	@Override
	public Starter getStarterById(Long id) {
		return starterRepository.findById(id).get();
	}

	@Override
	public Starter updateStarter(Starter starter) {
		return starterRepository.save(starter);
	}

	@Override
	public Starter saveStarter(Starter starter) {
		return starterRepository.save(starter);
	}

	@Override
	public void deleteStarterById(Long id) {
		starterRepository.deleteById(id);
		
	}

	@Override
	public List<Starter> getStartersByHotelId(Long hotelId) {
		return starterRepository.findAllByHotelId(hotelId);
	}

}
