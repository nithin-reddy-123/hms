package com.hms.controllers;

import com.hms.web.TableUserRegistration;
import com.hms.web.UserRegistration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TableController {

    @GetMapping("/counterlogin")
    public String counterLoginPage(Model model) {
        return "counterlogin";
    }

    @PostMapping("/counterlogin")
    public String validateUser(@ModelAttribute("counter") TableUserRegistration tableUserRegistration) {
        //dummy function
        return "counterlogin";
    }

    @GetMapping("/{hotelname}/orderhome")
    public String orderLoginPage(@PathVariable String hotelname, Model model)
    {
        model.addAttribute("hotelName",hotelname);
        return "orderhome";
    }
}

