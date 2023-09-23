package com.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hms.entities.Hotel;
import com.hms.service.HotelService;
import com.hms.service.UserService;
import com.hms.web.UserRegistration;


@Controller
public class CounterController {
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private UserService userService;
	
	//posts new counter
	/**@PostMapping("/counters/new")
	public String createCounter(@ModelAttribute Counter counterForm,Model model)
	{
		if(counterService.isCounterNumberTaken(counterForm.getCounterNumber()))
		{
			//System.out.println("it's error");
			logger.error("Counter number already taken: {}", counterForm.getCounterNumber());
			model.addAttribute("error", "Counter number already taken");
			return "create_counter";
		}
		logger.info("No problem");
		counterService.saveCounter(counterForm);
		return "redirect:/counters";
	}**/
	
	
	
	//creates new hotel
	@GetMapping("/hotels/new")
	public String createHotel(Model model)
	{
		Hotel hotel=new Hotel();
		model.addAttribute("hotel",hotel);
		return "create_hotel";
	}
	
	//to post hotels
	@PostMapping("/hotel")
	public String createHotel(@ModelAttribute("hotel") Hotel hotel)
	{
		hotelService.saveHotel(hotel);
		return "redirect:/hotel";
	}
		
	//to show hotels
	@GetMapping("/hotel")
	public String hotelsList(Model model)
	{
		model.addAttribute("hotels", hotelService.getAllHotels());
		return "hotel";
	}
	
	//to post updated hotels
	@PostMapping("/hotels/edited/{id}")
	public String updateHotel(@ModelAttribute("hotel") Hotel hotel)
	{
		hotelService.saveHotel(hotel);
		String hotelName=hotel.getHotelName();
		return "redirect:/"+hotelName+"/home";
	}
	
	//to return editing/updating hotels page
	@GetMapping("/hotels/edit/{id}")
	public String editByHotelId(@PathVariable Long id, Model model)
	{
		model.addAttribute("hotel",hotelService.getHotelById(id));
		return "edit_hotel";
	}

	@GetMapping("/{hotelname}/counters/{hid}")
	public String getCountersByHotelId(@PathVariable Long hid,@PathVariable String hotelname, Model model)
	{
		Hotel h=hotelService.getHotelById(hid);
		model.addAttribute("counters", h.getCounters());
		return "show_counters";
	}
	
	//to delete hotel
	@GetMapping("/hotels/{id}")
	public String deleteHotel(@PathVariable Long id)
	{
		hotelService.deleteHotelById(id);
		return "redirect:/hotel";
	}
	
	//creates login page
	@GetMapping("/login")
	public String Login(Model model)
	{
		Hotel hotel=new Hotel();
		model.addAttribute("hotel",hotel);// adds hotel attribute and this is used in login.html page
		return "login";
	}
	
	//posts home --this happens when submit is clicked on login page
	@PostMapping("/home")
	public String createHomeLogin(@ModelAttribute("hotel") Hotel hotel)
	{
		hotelService.saveHotel(hotel); //adds hotel to database
		String hotelName=hotel.getHotelName();
		return "redirect:/"+hotelName+"/home"; //redirects home page
	}
	
	//creates home
	@GetMapping("/{hotelname}/home")
	public String showYourHotel(@PathVariable String hotelname,Model model)
	{
		model.addAttribute("hotels", hotelService.getHotelByHotelName(hotelname)); // adds hotels attribute and this is used in home.html page to show list of all hotels
		return "home";
	}
	
	@GetMapping("/") 
	public String appHome(Model model)
	{
		return "apphome";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm() {
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUserAccount(@ModelAttribute("user") UserRegistration registration) {
		userService.save(registration);
		return "redirect:/register?success";
	}
	
	@GetMapping("/userlogin")
	public String login() {
		return "userlogin";
	}
}
