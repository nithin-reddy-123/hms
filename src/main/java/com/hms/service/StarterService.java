package com.hms.service;

import java.util.List;

import com.hms.entities.Starter;

public interface StarterService {
	Starter getStarterById(Long id);
	Starter updateStarter(Starter starter);
	Starter saveStarter(Starter starter);
	void deleteStarterById(Long id);
	List<Starter> getStartersByHotelId(Long hotelId);
}
