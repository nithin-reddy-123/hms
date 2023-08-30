package com.hms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hms.entities.Counter;
import com.hms.entities.Dessert;
import com.hms.entities.Hotel;
import com.hms.entities.Maincourse;
import com.hms.entities.Starter;
import com.hms.service.CounterService;
import com.hms.service.DessertService;
import com.hms.service.HotelService;
import com.hms.service.MaincourseService;
import com.hms.service.StarterService;

@Controller
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private StarterService starterService;
	
	@Autowired
	private MaincourseService maincourseService;
	
	@Autowired
	private DessertService dessertService;
	
	@Autowired
	private CounterService counterService;
	
	@GetMapping("/{hotelname}/menu")
	public String showMenu(@PathVariable String hotelname,Model model)
	{
		model.addAttribute("hotels", hotelService.getHotelByHotelName(hotelname));
		model.addAttribute("hotelName", hotelname);
		return "menu_home";
	}
	//********************************** Starters Start *******************************************
	
	//loads all starters
	@GetMapping("/{hotelname}/menu/starters")
	public String showStarters(@PathVariable String hotelname,Model model)
	{
		model.addAttribute("hotelName", hotelname);
		System.out.println("######################################");
		System.out.println(hotelname);
		System.out.println("######################################");
		Long hid=hotelService.findHotelIdByHotelName(hotelname);
		List<Starter>starters=starterService.getStartersByHotelId(hid);
		model.addAttribute("starters", starters); 
		return "starters";
	}
	
	//posts new starters
	@PostMapping("/{hotelname}/menu/starters")
	public String saveStarters(@PathVariable String hotelname,@ModelAttribute("starter") Starter starter)
	{
		Long hid=hotelService.findHotelIdByHotelName(hotelname);
		Hotel h=hotelService.getHotelById(hid);
		starter.setHotel(h);
		starterService.saveStarter(starter);
		return "redirect:/"+hotelname+"/menu/starters";
	}
	
    //loads new page to add starter
	@GetMapping("/{hotelname}/menu/starters/additem")
	public String addStarterItemsToMenu(@PathVariable String hotelname,Model model)
	{
		Long hid=hotelService.findHotelIdByHotelName(hotelname);
		Hotel h=hotelService.getHotelById(hid);
		Starter starter=new Starter();
		model.addAttribute("hotel", h);
		model.addAttribute("starter", starter);
		return "create_starter";
	}
	
	//loads new page to edit existing starter
	@GetMapping("/{hotelname}/menu/starters/edit/{id}")
	public String editItemsToMenu(@PathVariable String hotelname,@PathVariable Long id,Model model)
	{
		Long hid=hotelService.findHotelIdByHotelName(hotelname);
		Hotel hotel=hotelService.getHotelById(hid);
		model.addAttribute("hotel", hotel);
		Starter starter=starterService.getStarterById(id);
		model.addAttribute("starter", starter);
		return "edit_starter";
	}
	
	//posts new page to edit existing starter
	@PostMapping("/{hotelname}/menu/starters/edit/{id}")
	public String saveEditItemsToMenu(@PathVariable String hotelname,@ModelAttribute("starter") Starter starter)
	{
		Long hid=hotelService.findHotelIdByHotelName(hotelname);
		Hotel h=hotelService.getHotelById(hid);
		starter.setHotel(h);
		starterService.saveStarter(starter);
		return "redirect:/"+hotelname+"/menu/starters";
	}
	
	//loads page again after deleting existing starter
	@GetMapping("/{hotelname}/menu/starters/delete/{id}")
	public String deleteItemsFromMenu(@PathVariable String hotelname, @PathVariable Long id,Model model)
	{
		starterService.deleteStarterById(id);
		return "redirect:/menu_home";
	}
	
	//******************************************** Starters end *******************************************
	//******************************************** Maincourses Start **************************************
	
	//loads page to show existing maincourses
	@GetMapping("/{hotelname}/menu/maincourses")
	public String showMaincourse(@PathVariable String hotelname, Model model)
	{
		model.addAttribute("hotelName", hotelname);
		Long hid=hotelService.findHotelIdByHotelName(hotelname);
		List<Maincourse>maincourses=maincourseService.getMaincoursesByHotelId(hid);
		model.addAttribute("maincourses", maincourses);
		return "maincourses";
	}
	
	//loads new page to add maincourse
	@GetMapping("/{hotelname}/menu/maincourse/additem")
	public String addMainCourseItemsToMenu(@PathVariable String hotelname,Model model)
	{
		Long hid=hotelService.findHotelIdByHotelName(hotelname);
		Hotel h=hotelService.getHotelById(hid);
		Maincourse maincourse=new Maincourse();
		model.addAttribute("hotel", h);
		model.addAttribute("maincourse", maincourse);
		return "create_maincourse";
	}
	
	//posts new maincourses
	@PostMapping("/{hotelname}/menu/maincourses")
	public String saveMaincourses(@PathVariable String hotelname,@ModelAttribute("maincourse") Maincourse maincourse)
	{
		Long hid=hotelService.findHotelIdByHotelName(hotelname);
		Hotel h=hotelService.getHotelById(hid);
		maincourse.setHotel(h);
		maincourseService.saveMaincourse(maincourse);
		return "redirect:/"+hotelname+"/menu/maincourses";
	}
	
	//loads new page to edit existing maincourse
	@GetMapping("/{hotelname}/menu/maincourses/edit/{id}")
	public String editMaincourseItemsToMenu(@PathVariable String hotelname,@PathVariable Long id,Model model)
	{
		Long hid=hotelService.findHotelIdByHotelName(hotelname);
		Hotel hotel=hotelService.getHotelById(hid);
		model.addAttribute("hotel", hotel);
		Maincourse maincourse=maincourseService.getMaincourseById(id);
		model.addAttribute("maincourse", maincourse);
		return "edit_maincourse";
	}
	
	//posts after editing existing maincourse
	@PostMapping("/{hotelname}/menu/maincourses/edit/{id}")
	public String saveEditedMaincourseItemsToMenu(@PathVariable String hotelname,@ModelAttribute("maincourse") Maincourse maincourse)
	{
		Long hid=hotelService.findHotelIdByHotelName(hotelname);
		Hotel h=hotelService.getHotelById(hid);
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%"+maincourse.getMaincoursePrice()+"%%%%%%%%%%%%%%%%%%%%%");
		maincourse.setHotel(h);
		maincourseService.saveMaincourse(maincourse);
		System.out.println("%%%%%%%%%%"+maincourse.getId()+"%%%%%%%%%%%%"+maincourse.getMaincoursePrice()+"%%%%%%%%%%%%%%%%%%%%%");
		return "redirect:/"+hotelname+"/menu/maincourses";
	}
	
	//loads page again after deleting existing maincourse
	@GetMapping("/{hotelname}/menu/maincourses/delete/{id}")
	public String deleteMaincourseItemsFromMenu(@PathVariable String hotelname, @PathVariable Long id,Model model)
	{
		maincourseService.deleteMaincourseById(id);
		return "redirect:/"+hotelname+"/menu/maincourses";
	}
	
	//****************************************** Maincourses End *****************************************
	//****************************************** Desserts Start ******************************************
	
	@GetMapping("/{hotelname}/menu/desserts")
	public String showDesserts(@PathVariable String hotelname, Model model)
	{
		model.addAttribute("hotelName", hotelname);
		Long hid=hotelService.findHotelIdByHotelName(hotelname);
		List<Dessert>desserts=dessertService.getDessertsByHotelId(hid);
		model.addAttribute("desserts", desserts);
		return "desserts";
	}
	
	//loads new page to add dessert
	@GetMapping("/{hotelname}/menu/desserts/additem")
	public String addDessertItemsToMenu(@PathVariable String hotelname,Model model)
	{
		Long hid=hotelService.findHotelIdByHotelName(hotelname);
		Hotel h=hotelService.getHotelById(hid);
		Dessert dessert=new Dessert();
		model.addAttribute("hotel", h);
		model.addAttribute("dessert", dessert);
		return "create_dessert";
	}
	
	//posts new desserts
	@PostMapping("/{hotelname}/menu/desserts")
	public String saveDesserts(@PathVariable String hotelname,@ModelAttribute("dessert") Dessert dessert)
	{
		Long hid=hotelService.findHotelIdByHotelName(hotelname);
		Hotel h=hotelService.getHotelById(hid);
		dessert.setHotel(h);
		dessertService.saveDessert(dessert);
		return "redirect:/"+hotelname+"/menu/desserts";
	}
		
	//loads new page to edit existing dessert
	@GetMapping("/{hotelname}/menu/desserts/edit/{id}")
	public String editDessertItemsToMenu(@PathVariable String hotelname,@PathVariable Long id,Model model)
	{
		Long hid=hotelService.findHotelIdByHotelName(hotelname);
		Hotel hotel=hotelService.getHotelById(hid);
		model.addAttribute("hotel", hotel);
		Dessert dessert=dessertService.getDessertById(id);
		model.addAttribute("dessert", dessert);
		return "edit_dessert";
	}
		
	//posts after editing existing dessert
	@PostMapping("/{hotelname}/menu/desserts/edit/{id}")
	public String saveEditedDessertItemsToMenu(@PathVariable String hotelname,@ModelAttribute("dessert") Dessert dessert)
	{
		Long hid=hotelService.findHotelIdByHotelName(hotelname);
		Hotel h=hotelService.getHotelById(hid);
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%"+dessert.getDessertPrice()+"%%%%%%%%%%%%%%%%%%%%%");
		dessert.setHotel(h);
		dessertService.saveDessert(dessert);
		System.out.println("%%%%%%%%%%"+dessert.getId()+"%%%%%%%%%%%%"+dessert.getDessertPrice()+"%%%%%%%%%%%%%%%%%%%%%");
		return "redirect:/"+hotelname+"/menu/desserts";
	}
		
	//loads page again after deleting existing dessert
	@GetMapping("/{hotelname}/menu/desserts/delete/{id}")
	public String deleteDessertItemsFromMenu(@PathVariable String hotelname, @PathVariable Long id,Model model)
	{
		dessertService.deleteDessertById(id);
		return "redirect:/"+hotelname+"/menu/desserts";
	}
	
	//**************************************** Desserts End ********************************************
	//**************************************** Counters Start ******************************************
	
	//creates new counter
	@GetMapping("/{hotelname}/counters/new")
	public String createCounterForm(@PathVariable String hotelname, Model model)
	{
		Long hid=hotelService.findHotelIdByHotelName(hotelname);
		Hotel h=hotelService.getHotelById(hid);
		Counter counter=new Counter();
		model.addAttribute("hotel", h);
		model.addAttribute("counter",counter);
		return "create_counter";
	}
		
	//to post counters
	@PostMapping("/{hotelname}/counters")
	public String createCounter(@PathVariable String hotelname, @ModelAttribute("counter") Counter counter)
	{
		Long hid=hotelService.findHotelIdByHotelName(hotelname);
		Hotel h=hotelService.getHotelById(hid);
		counter.setHotel(h);
		counterService.saveCounter(counter);
		return "redirect:/"+hotelname+"/counters";
	}
		
	//to show counters
	@GetMapping("/{hotelname}/counters")
	public String countersList(@PathVariable String hotelname, Model model)
	{
		model.addAttribute("hotelName", hotelname);
		Long hid=hotelService.findHotelIdByHotelName(hotelname);
		List<Counter>counters=counterService.getCountersByHotelId(hid);
		model.addAttribute("counters", counters);
		return "counters";
	}

	//to delete counter
	@GetMapping("/{hotelname}/counters/delete/{id}")
	public String deleteCounter(@PathVariable String hotelname, @PathVariable Long id)
	{
		counterService.deleteCounterById(id);
		return "redirect:/"+hotelname+"/counters";
	}

	//to return editing/updating counters page
	@GetMapping("/{hotelname}/counters/edit/{id}")
	public String editCountersByHotelId(@PathVariable String hotelname, @PathVariable Long id, Model model)
	{
		Long hid=hotelService.findHotelIdByHotelName(hotelname);
		Hotel hotel=hotelService.getHotelById(hid);
		model.addAttribute("hotel", hotel);
		model.addAttribute("counter",counterService.getCounterById(id));
		return "edit_counter";
	}
		
	//to post updated counters
	@PostMapping("/{hotelname}/counters/edited/{id}")
	public String updateCounter(@PathVariable String hotelname, @ModelAttribute("counter") Counter counter)
	{
		Long hid=hotelService.findHotelIdByHotelName(hotelname);
		Hotel h=hotelService.getHotelById(hid);
		counter.setHotel(h);
		counterService.saveCounter(counter);
		return "redirect:/"+hotelname+"/counters";
	}
	
	//************************************************* Desserts End ****************************************
		
	@GetMapping("/{hotelname}/menu/billsummary")
	public String showBillsummary(@PathVariable String hotelname, Model model)
	{
		model.addAttribute("hotelName", hotelname);
		return "billsummary";
	}

}
