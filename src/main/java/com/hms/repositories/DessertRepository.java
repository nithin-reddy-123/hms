package com.hms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entities.Dessert;

public interface DessertRepository extends JpaRepository<Dessert, Long>{

	List<Dessert> findAllByHotelId(Long hotelId);
}
