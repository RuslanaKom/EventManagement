package lt.viko.rkomaristova.eventmanagement.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lt.viko.rkomaristova.eventmanagement.dto.UserRegistrationDto;
import lt.viko.rkomaristova.eventmanagement.entities.Event;
import lt.viko.rkomaristova.eventmanagement.entities.Ticket;
import lt.viko.rkomaristova.eventmanagement.entities.User;
import lt.viko.rkomaristova.eventmanagement.entities.UserDetails;
import lt.viko.rkomaristova.eventmanagement.resourcesupports.EventResource;
import lt.viko.rkomaristova.eventmanagement.services.EventService;
import lt.viko.rkomaristova.eventmanagement.services.TicketService;
import lt.viko.rkomaristova.eventmanagement.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	EventService eventService;
	
	@PostMapping(value = "/register", produces = "application/json")
	public  @ResponseBody boolean registerSimpleUser(@RequestBody UserRegistrationDto userDto) {
		return userService.saveNewUser(userDto);
	}

//	@GetMapping(value = "/login", produces = "application/json")
//	public  @ResponseBody User loginUser(@RequestParam String username, @RequestParam String password) {
//		return userService.checkLogin(username, password);
//	}
//	
	@GetMapping(value = "", produces = "application/json")
	public  @ResponseBody List<User> findUsers() {
		return userService.findAllUsers();
	}
	
	@GetMapping(value = "/id", produces = "application/json")
	public  @ResponseBody List<User> findUserById (@RequestParam Long id){
		return userService.findUserById(id);
	}
	
	@GetMapping(value = "/{id}/tickets", produces = "application/json")
	public  @ResponseBody List<Ticket> findUserTickets (@PathVariable String id){
		return ticketService.getTicketsByUser(Long.parseLong(id));
	}
	
//	@GetMapping(value = "/{id}/favourites", produces = "application/hal+json")
//	public  @ResponseBody List<EventResource> findUserFavouriteEvents (@PathVariable String id){
//		List<Event> events = eventService.getUserFavouriteEvents(Long.parseLong(id));
//		return events.stream().map(e -> new EventResource(e)).collect(Collectors.toList());
//	}
	
	@GetMapping(value = "/favourites", produces = "application/hal+json")
	public  @ResponseBody List<EventResource> findUserFavouriteEvents (Principal principal){
		String username = principal.getName();
		UserDetails user = (UserDetails) userService.loadUserByUsername(username);
		List<Event> events = eventService.getUserFavouriteEvents(user.getUser().getId());
		return events.stream().map(e -> new EventResource(e)).collect(Collectors.toList());
	}

	@GetMapping(value = "/username", produces = "application/json")
	public @ResponseBody String currentUserId(Principal principal) {
	    String username =principal.getName();
	    UserDetails user = (UserDetails) userService.loadUserByUsername(username);
	    String id = user.getUser().getId().toString();
        return id;
	    }
}
