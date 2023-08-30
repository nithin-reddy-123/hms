package com.hms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entities.Starter;

public interface StarterRepository extends JpaRepository<Starter, Long>{

	List<Starter> findAllByHotelId(Long hotelId);
}
