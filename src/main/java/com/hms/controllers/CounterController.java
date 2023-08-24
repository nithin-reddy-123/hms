package com.hms.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hms.entities.Counter;
import com.hms.entities.Hotel;
import com.hms.service.CounterService;
import com.hms.service.HotelService;


@Controller
public class CounterController {


    private final Logger logger = LoggerFactory.getLogger(CounterController.class);

	@Autowired
	private CounterService counterService;
	
	@Autowired
	private HotelService hotelService;
	
	
	//posts new counter
	@PostMapping("/counters/new")
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
		/**String hotelname=counterForm.getHotelName();
		Long hid=counterService.findHotelIdByHotelName(hotelname);
		Hotel h=counterService.getHotelById(hid);**/
		counterService.saveCounter(counterForm);
		return "redirect:/counters";
	}
	
	//creates new counter
	@GetMapping("/counters/new")
	public String createCounterForm(Model model)
	{
		Counter counter=new Counter();
		model.addAttribute("counter",counter);
		return "create_counter";
	}
		
	//creates new hotel
	@GetMapping("/hotels/new")
	public String createHotel(Model model)
	{
		Hotel hotel=new Hotel();
		model.addAttribute("hotel",hotel);
		return "create_hotel";
	}
	
	//to post counters
	@PostMapping("/counters")
	public String createCounter(@ModelAttribute("counter") Counter counter)
	{
		String hotelname=counter.getHotelName();
		System.out.println("===============================================================================");
		System.out.println(hotelname);
		Long hid=hotelService.findHotelIdByHotelName(hotelname);
		System.out.println("===============================================================================");
		System.out.println(hid);
		Hotel h=hotelService.getHotelById(hid);
		System.out.println("===============================================================================");
		System.out.println(h);
		counter.setHotel(h);
		counterService.saveCounter(counter);
		return "redirect:/counters";
	}
	
	//to post hotels
	@PostMapping("/hotel")
	public String createHotel(@ModelAttribute("hotel") Hotel hotel)
	{
		hotelService.saveHotel(hotel);
		return "redirect:/hotel";
	}
	
	//to show counters
	@GetMapping("/counters")
	public String countersList(Model model)
	{
		List<Counter> distinctCounters = counterService.getAllCounters().stream()
                .distinct()
                .collect(Collectors.toList());
		model.addAttribute("counters", distinctCounters );
		return "counters";
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
		return "redirect:/hotel";
	}
	
	//to post updated counters
	@PostMapping("/counters/edited/{id}")
	public String updateCounter(@ModelAttribute("counter") Counter counter)
	{
		String updatedHotelName = counter.getHotelName();
		System.out.println("*******************************************************************************");
		System.out.println(updatedHotelName);
		List<Hotel> hotelsWithUpdatedName = hotelService.getHotelByHotelName(updatedHotelName);
	    
	    if (!hotelsWithUpdatedName.isEmpty()) {
	        // Assuming there's only one hotel with the updated name
	        Hotel updatedHotel = hotelsWithUpdatedName.get(0);
	        
	        // Set the updated hotel for the counter
	        counter.setHotel(updatedHotel);
	        
	        // Save the counter with the updated hotel association
	        counterService.saveCounter(counter);
	    }
		return "redirect:/counters";
	}
	
	//to return editing/updating hotels page
	@GetMapping("/hotels/edit/{id}")
	public String editByHotelId(@PathVariable Long id, Model model)
	{
		model.addAttribute("hotel",hotelService.getHotelById(id));
		return "edit_hotel";
	}
	
	//to return editing/updating counters page
	@GetMapping("/counters/edit/{id}")
	public String editCountersByHotelId(@PathVariable Long id, Model model)
	{
		model.addAttribute("counter",counterService.getCounterById(id));
		return "edit_counter";
	}

	@GetMapping("/{hotelname}/counters/{id}")
	public String getCountersByHotelId(@PathVariable Long id,@PathVariable String hotelname, Model model)
	{
		Hotel h=hotelService.getHotelById(id);
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
	
	//to delete counter
	@GetMapping("/counters/{id}")
	public String deleteCounter(@PathVariable Long id)
	{
		counterService.deleteCounterById(id);
		return "redirect:/counters";
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
	
	@GetMapping("/menu")
	public String showMenu(Model model)
	{
		return "menu";
	}
	
	@GetMapping("/starters")
	public String showStarters(Model model)
	{
		return "starters";
	}
	
	@GetMapping("/maincourse")
	public String showMaincourse(Model model)
	{
		return "maincourse";
	}
	
	@GetMapping("/desserts")
	public String showDesserts(Model model)
	{
		return "desserts";
	}
	
	@GetMapping("/billsummary")
	public String showBillsummary(Model model)
	{
		return "billsummary";
	}
	
}
