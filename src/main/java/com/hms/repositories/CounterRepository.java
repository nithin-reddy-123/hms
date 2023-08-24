package com.hms.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entities.Counter;

public interface CounterRepository extends JpaRepository<Counter, Long>{

	boolean existsByCounterNumber(Integer counterNumber);
	
	

}
