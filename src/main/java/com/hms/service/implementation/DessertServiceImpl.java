package com.hms.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.entities.Dessert;
import com.hms.repositories.DessertRepository;
import com.hms.service.DessertService;

@Service
public class DessertServiceImpl implements DessertService{

	@Autowired
	private DessertRepository dessertRepository;
	@Override
	public Dessert getDessertById(Long id) {
		return dessertRepository.findById(id).get();
	}

	@Override
	public Dessert updateDessert(Dessert dessert) {
		return dessertRepository.save(dessert);
	}

	@Override
	public Dessert saveDessert(Dessert dessert) {
		return dessertRepository.save(dessert);
	}

	@Override
	public void deleteDessertById(Long id) {
		dessertRepository.deleteById(id);
		
	}

	@Override
	public List<Dessert> getDessertsByHotelId(Long hotelId) {
		return dessertRepository.findAllByHotelId(hotelId);
	}

}
