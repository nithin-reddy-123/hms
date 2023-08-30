package com.hms.service;

import java.util.List;

import com.hms.entities.Dessert;

public interface DessertService {
	Dessert getDessertById(Long id);
	Dessert updateDessert(Dessert dessert);
	Dessert saveDessert(Dessert dessert);
	void deleteDessertById(Long id);
	List<Dessert> getDessertsByHotelId(Long hotelId);
}
