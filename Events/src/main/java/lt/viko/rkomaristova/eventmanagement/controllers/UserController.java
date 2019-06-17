package lt.viko.rkomaristova.eventmanagement.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;
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

import io.swagger.annotations.ApiOperation;
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

	@ApiOperation(value = "Registers new user", notes = "Creates User with account and persists it. If username entered already exists user is not registered and false is returned.")
	@PostMapping(value = "/register", produces = "application/json")
	public @ResponseBody boolean registerSimpleUser(@RequestBody UserRegistrationDto userDto) {
		return userService.saveNewUser(userDto);
	}

//	@GetMapping(value = "/login", produces = "application/json")
//	public  @ResponseBody User loginUser(@RequestParam String username, @RequestParam String password) {
//		return userService.checkLogin(username, password);
//	}

	@ApiOperation(value = "Finds all users", notes = "Returns a list of all users available.")
	@GetMapping(value = "", produces = "application/json")
	public @ResponseBody List<User> findUsers() {
		return userService.findAllUsers();
	}

	@ApiOperation(value = "Finds user by id", notes = "Returns user found by id.")
	@GetMapping(value = "/{id}", produces = "application/json")
	public @ResponseBody List<User> findUserById(@PathVariable Long id) {
		return userService.findUserById(id);
	}

	@ApiOperation(value = "Finds user's tickets", notes = "Returns tickets of user currently logged in. If no user is logged in throws NoResultException.")
	@GetMapping(value = "/tickets", produces = "application/json")
	public @ResponseBody List<Ticket> findUserTickets(Principal principal) {
		if (principal == null) {
			throw new NoResultException("No user is logged in");
		}
		return ticketService.getTicketsByUser(principal.getName());
	}
	
	@ApiOperation(value = "Finds user's favourite events", notes = "Returns favourite events of user currently logged in. If no user is logged in throws NoResultException.")
	@GetMapping(value = "/favourites", produces = "application/hal+json")
	public @ResponseBody List<EventResource> findUserFavouriteEvents(Principal principal) {
		if (principal == null) {
			throw new NoResultException("No user is logged in");
		}
		List<Event> events = eventService.getUserFavouriteEvents(principal.getName());
		return events.stream().map(e -> new EventResource(e)).collect(Collectors.toList());
	}

	@ApiOperation(value = "Finds id of the current user", notes = "Returns id of user currently logged in. If no user is logged in throws NoResultException.")
	@GetMapping(value = "/username", produces = "application/json")
	public @ResponseBody String currentUserId(Principal principal) {
		if (principal == null) {
			throw new NoResultException("No user is logged in");
		}
		String username = principal.getName();
		UserDetails user = (UserDetails) userService.loadUserByUsername(username);
		String id = user.getUser().getId().toString();
		return id;
	}
}
