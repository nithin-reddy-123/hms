package com.hms.service.implementation;

import com.hms.entities.Hotel;
import com.hms.entities.Role;
import com.hms.entities.TableUser;
import com.hms.repositories.HotelRepository;
import com.hms.repositories.TableUserRepository;
import com.hms.service.TableUserService;
import com.hms.web.TableUserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class TableUserServiceImpl implements TableUserService {

    @Autowired
    private TableUserRepository tableUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        TableUser tableUser = tableUserRepository.findByEmail(emailAddress);
        if(tableUser == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(tableUser.getEmail(), tableUser.getPassword(), mapRolesToAuthorities(tableUser.getRoles()));
    }

    @Override
    public TableUser save(TableUserRegistration tableUserRegistration) {
        Role role=new Role();
        role.setName("USER");
        TableUser tableUser = new TableUser();
        tableUser.setHotelName(tableUserRegistration.getHotelName());
        tableUser.setCounterNumber(tableUserRegistration.getCounterNumber());
        tableUser.setEmail(tableUserRegistration.getEmail());
        tableUser.setPassword(bCryptPasswordEncoder.encode(tableUserRegistration.getPassword()));
        tableUser.setRoles(Arrays.asList(role));
        return tableUserRepository.save(tableUser);
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
