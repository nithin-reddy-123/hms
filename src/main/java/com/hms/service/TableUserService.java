package com.hms.service;

import com.hms.entities.TableUser;
import com.hms.entities.User;
import com.hms.web.TableUserRegistration;
import com.hms.web.UserRegistration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface TableUserService extends UserDetailsService {

    TableUser save(TableUserRegistration tableUserRegistration);

    UserDetails loadUserByUsername(String emailAddress);
}
