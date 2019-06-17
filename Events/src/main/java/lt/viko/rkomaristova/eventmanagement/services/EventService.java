package lt.viko.rkomaristova.eventmanagement.services;

import java.util.List;

import org.jboss.logging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import lt.viko.rkomaristova.eventmanagement.dao.EventDao;
import lt.viko.rkomaristova.eventmanagement.dto.EventDto;
import lt.viko.rkomaristova.eventmanagement.entities.AgendaPoint;
import lt.viko.rkomaristova.eventmanagement.entities.Event;

@Service
public class EventService {

	@Autowired
	private EventDao eventDao;

	public void deleteEventById(Long id) {
		eventDao.deleteEventById(id);
	}

	public List<Event> getEventByName(String name) {
		return eventDao.findEventByName(name);
	}

	public List<Event> getAllEvents() {
		return eventDao.findAllEvents();
	}

	public Event getEventById(Long id) throws NotFoundException {
		List<Event> events = eventDao.findEventById(id);
		if (!events.isEmpty()) {
			return events.get(0);
		}
		throw new NotFoundException(String.format("Event with id %d not found", id));
	}

	public List<Event> getEventsByCriteria(String name, String city, String category, Boolean isFree, Boolean future) {
		return eventDao.findEventByCriteria(name, city, category, isFree, future);
	}

	public EventDto getFullEvent(Long eventId) {
		Event event = eventDao.findEventWithDetails(eventId).get(0);
		List<AgendaPoint> agenda = eventDao.findAgendaByEvent(event);
		return new EventDto(event, agenda);
	}
	
	public List<Event> getUserFavouriteEvents(String username) {
		return eventDao.findUserFavouriteEvents(username);
	}
}
