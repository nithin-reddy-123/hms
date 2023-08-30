package com.hms.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hms.entities.Counter;

public interface CounterRepository extends JpaRepository<Counter, Long>{

	List<Counter> findAllByHotelId(Long hotelId);
	

}
