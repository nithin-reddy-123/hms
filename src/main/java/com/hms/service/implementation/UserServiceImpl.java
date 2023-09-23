package com.hms.service.implementation;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hms.entities.Role;
import com.hms.entities.User;
import com.hms.repositories.UserRepository;
import com.hms.service.UserService;
import com.hms.web.UserRegistration;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
	}

	@Override
	public User save(UserRegistration userRegistration) {
		Role role=new Role();
		role.setName("USER");
		User user = new User();
		user.setFirstName(userRegistration.getFirstName());
		user.setLastName(userRegistration.getLastName());
		user.setEmail(userRegistration.getEmail());
		user.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
		user.setRoles(Arrays.asList(role));
		return userRepository.save(user);
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
