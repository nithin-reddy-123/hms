package com.hms.security;

import com.hms.entities.Hotel;
import com.hms.entities.User;
import com.hms.repositories.HotelRepository;
import com.hms.service.HotelService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler1 extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException
    {
        System.out.println("In class1: "+authentication.getPrincipal().getClass());
        if(authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User)
        {
            org.springframework.security.core.userdetails.User user=(org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            String emailAddress= user.getUsername();
            System.out.println("In class1: "+emailAddress);
            Hotel hotel=(Hotel) hotelRepository.findHotelByEmail(emailAddress);
            String hotelName=hotel.getHotelName();
            setDefaultTargetUrl("/"+hotelName+"/home");
            System.out.println(hotelName);
        }
        super.onAuthenticationSuccess(request,response,authentication);
    }
}
