package lt.viko.rkomaristova.eventmanagement.resourcesupports;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkBuilder;

import lt.viko.rkomaristova.eventmanagement.controllers.EventController;
import lt.viko.rkomaristova.eventmanagement.entities.Event;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class EventResource extends ResourceSupport {

	private Event event;

	public EventResource(Event event) {
		this.event = event;
		final long id = event.getId();
		if (!event.getIsFree()) {
			add(linkTo(EventController.class).slash(id).slash("buy").withRel("buy"));
		}
		add(linkTo(EventController.class).slash(id).slash("favourite").withRel("favourite"));
		add(linkTo(EventController.class).slash(id).slash("unfavourite").withRel("unfavourite"));
		add(linkTo(methodOn(EventController.class).getEventWithDetails(id)).withRel("details"));
		add(linkTo(EventController.class).slash(id).slash("feedback").withRel("postfeedback"));
		add(linkTo(EventController.class).slash(id).slash("allfeedback").withRel("getfeedback"));
	}

	public Event getEvent() {
		return event;
	}
}
