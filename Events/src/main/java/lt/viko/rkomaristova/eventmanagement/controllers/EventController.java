package lt.viko.rkomaristova.eventmanagement.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lt.viko.rkomaristova.eventmanagement.dto.EventDto;
import lt.viko.rkomaristova.eventmanagement.entities.Event;
import lt.viko.rkomaristova.eventmanagement.entities.Feedback;
import lt.viko.rkomaristova.eventmanagement.entities.Ticket;
import lt.viko.rkomaristova.eventmanagement.entities.User;
import lt.viko.rkomaristova.eventmanagement.resourcesupports.EventResource;
import lt.viko.rkomaristova.eventmanagement.services.EventService;
import lt.viko.rkomaristova.eventmanagement.services.FeedbackService;
import lt.viko.rkomaristova.eventmanagement.services.TicketService;
import lt.viko.rkomaristova.eventmanagement.services.UserService;

@RestController
@RequestMapping(value ="/events",produces = "application/hal+json")
public class EventController {
	
	@Autowired
	EventService eventService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	FeedbackService feedbackService;	

	@GetMapping(value = "/all", produces = "application/json")
	public  @ResponseBody List<Event> getEvents() {
		return eventService.getAllEvents();
	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public  @ResponseBody Event getEventById(@PathVariable Long id) {
		return  eventService.getEventById(id);
	}
	
	@GetMapping(value = "", produces = "application/hal+json")
	public @ResponseBody List<EventResource> getEventsByCriteria(@RequestParam(required = false) Optional<String> name,
																@RequestParam(required = false) Optional<String> city,
																@RequestParam(required = false) Optional<String> category,
																@RequestParam(required = false) Optional<Boolean> isFree,
																@RequestParam(required = false) Optional<Boolean> future) {
		List<Event> events = eventService.getEventsByCriteria(name.orElse(null), city.orElse(null),
				category.orElse(null), isFree.orElse(null), future.orElse(null));
		return events.stream().map(e -> new EventResource(e)).collect(Collectors.toList());
	}
	
	/*________DETAILS__________*/
	
	@GetMapping(value = "/{id}/details", produces = "application/json")
	public  @ResponseBody EventDto getEventWithDetails(@PathVariable Long id) {
		EventDto event =  eventService.getFullEvent(id);
		return event;
	}
	
	/*_________TICKET__________*/
	
	@PostMapping(value = "/{id}/buy", produces = "application/json")
	public  @ResponseBody Ticket buyEventTicket(@PathVariable Long id, @RequestParam Long userId) {
		Event event =  eventService.getEventById(id);
		User user = userService.findUserById(userId).get(0);
		return ticketService.buyTicket(event, user);
	}
	
	 /*________FAVOURITES________*/
	
	@PutMapping(value = "/{id}/favourite", produces = "application/json")
	public  @ResponseBody ResponseEntity saveEventToFavourite(@PathVariable Long id, @RequestParam Long userId) {
		Event event =  eventService.getEventById(id);
		User user = userService.findUserById(userId).get(0);
		userService.addEventToFavourites(user, event);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@DeleteMapping(value = "/{id}/favourite", produces = "application/json")
	public  @ResponseBody ResponseEntity deleteEventFromFavourite(@PathVariable Long id, @RequestParam Long userId) {
		Event event =  eventService.getEventById(id);
		User user = userService.findUserById(userId).get(0);
		userService.removeEventFromFavourites(user, event);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	 /*_________FEEDBACK_________*/
	
	@PostMapping(value = "/{id}/feedback", produces = "application/json")
	public  @ResponseBody ResponseEntity saveFeedback(@PathVariable Long id, @RequestParam String feedback, @RequestParam Long userId) {
		Event event =  eventService.getEventById(id);
		User user = userService.findUserById(userId).get(0);
		feedbackService.saveFeedback(user, event, feedback);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@GetMapping(value = "/{id}/feedback", produces = "application/json")
	public  @ResponseBody List<Feedback> saveFeedback(@PathVariable Long id) {
		return feedbackService.getEventFeedbacks(id);
	}

}
