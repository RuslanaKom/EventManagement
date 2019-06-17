package lt.viko.rkomaristova.eventmanagement.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
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
	
	@ApiOperation(value = "Returns all events", notes = "Returns not filtered list of all events available.")
	@GetMapping(value = "/all", produces = "application/json")
	public  @ResponseBody List<Event> getEvents() {
		return eventService.getAllEvents();
	}
	
	@ApiOperation(value = "Finds event by id", notes = "Returns single event found by id or throws NotFoundException if event is not found.")
	@GetMapping(value = "/{id}", produces = "application/json")
	public  @ResponseBody Event getEventById(@PathVariable Long id) throws NotFoundException {
		return  eventService.getEventById(id);
	}
	
	@ApiOperation(value = "Finds events by different criteria", notes = "Returns list of available events filtered out by several criteria. Criteria are optional, filtration is done only by criteria which were passed.")
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
	
	@ApiOperation(value = "Finds event by id with the event details included", notes = "Return sevent with it's detailed agenda, organisators, participants and equipment.")
	@GetMapping(value = "/{id}/details", produces = "application/json")
	public  @ResponseBody EventDto getEventWithDetails(@PathVariable Long id) {
		EventDto event =  eventService.getFullEvent(id);
		return event;
	}
	
	/*_________TICKET__________*/
	
	@ApiOperation(value = "Creates event ticket for user currently logged in", notes = "Creates new ticket and saves it under user currently logged in. If no user is logged in throws NoResultException. If event if free and ticket cannot be purchased throws IllegalArgumentException.")
	@PostMapping(value = "/{id}/buy", produces = "application/json")
	public  @ResponseBody Ticket buyEventTicket(@PathVariable Long id, Principal principal) throws NotFoundException {
		if (principal == null) {
			throw new NoResultException("No user is logged in");
		}
		Event event =  eventService.getEventById(id);
		User user = userService.findUserByUsername(principal.getName());
		return ticketService.buyTicket(event, user);
	}
	
	 /*________FAVOURITES________*/
	
	@ApiOperation(value = "Adds event to user favourites", notes = "Adds event by it's id to currently logged in user favourites. If no user is logged in throws NoResultException.")
	@PutMapping(value = "/{id}/favourite", produces = "application/json")
	public  @ResponseBody ResponseEntity saveEventToFavourite(@PathVariable Long id, Principal principal) throws NotFoundException {
		if (principal == null) {
			throw new NoResultException("No user is logged in");
		}
		Event event =  eventService.getEventById(id);
		User user = userService.findUserByUsername(principal.getName());
		userService.addEventToFavourites(user, event);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@ApiOperation(value = "Removes event from user favourites", notes = "Removed event by it's id from currently logged in user favourites. If no user is logged in throws NoResultException.")
	@DeleteMapping(value = "/{id}/favourite", produces = "application/json")
	public  @ResponseBody ResponseEntity deleteEventFromFavourite(@PathVariable Long id, Principal principal) throws NotFoundException {
		if (principal == null) {
			throw new NoResultException("No user is logged in");
		}
		Event event =  eventService.getEventById(id);
		User user = userService.findUserByUsername(principal.getName());
		userService.removeEventFromFavourites(user, event);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	 /*_________FEEDBACK_________*/
	
	@ApiOperation(value = "Adds feedback to event", notes = "Adds feedback from currently logged in user to event indicated by id. If no user is logged in throws NoResultException.")
	@PostMapping(value = "/{id}/feedback", produces = "application/json")
	public  @ResponseBody ResponseEntity saveFeedback(@PathVariable Long id, @RequestParam String feedback, Principal principal) throws NotFoundException {
		if (principal == null) {
			throw new NoResultException("No user is logged in");
		}
		Event event =  eventService.getEventById(id);
		User user = userService.findUserByUsername(principal.getName());
		feedbackService.saveFeedback(user, event, feedback);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@ApiOperation(value = "Returns all feedback for event", notes = "Returns list of all available feedbacks for event indicated by id.")
	@GetMapping(value = "/{id}/feedback", produces = "application/json")
	public  @ResponseBody List<Feedback> getFeedback(@PathVariable Long id) {
		return feedbackService.getEventFeedbacks(id);
	}
}
