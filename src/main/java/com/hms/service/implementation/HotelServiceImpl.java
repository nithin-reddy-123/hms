package com.hms.service.implementation;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.hms.entities.Role;
import com.hms.entities.User;
import com.hms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hms.entities.Counter;
import com.hms.entities.Hotel;
import com.hms.repositories.HotelRepository;
import com.hms.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Hotel> getAllHotels() {
		
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotelById(Long id) {

		return hotelRepository.findById(id).get();
	}

	@Override
	public Hotel updateHotel(Hotel hotel) {

		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel saveHotel(Hotel hotel) {

		return hotelRepository.save(hotel);
	}

	@Override
	public void deleteHotelById(Long id) {

		hotelRepository.deleteById(id);
		
	}

	@Override
	public List<Counter> getCountersById(Long id) {
		
		Hotel hotel=hotelRepository.findById(id).get();
		List<Counter>counters=hotel.getCounters();
		counters.forEach(x->System.out.println(x));
		return counters;
	}

	@Override
	public List<Hotel> getHotelByHotelName(String hotelName) {
		List<Hotel>hotels=hotelRepository.findAllByHotelName(hotelName);
		return hotels;
	}

	@Override
	public Long findHotelIdByHotelName(String hotelName) {
		return hotelRepository.findHotelIdByHotelName(hotelName);
	}

	@Override
	public Hotel findHotelByEmail(String emailAddress) {
		return hotelRepository.findHotelByEmail(emailAddress);
	}


	@Override
	public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
		Hotel hotel=hotelRepository.findHotelByEmail(emailAddress);
		User user = userRepository.findByEmail(emailAddress);
		if(hotel == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(hotel.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
