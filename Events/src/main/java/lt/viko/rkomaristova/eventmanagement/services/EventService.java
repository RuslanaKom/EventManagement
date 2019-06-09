package lt.viko.rkomaristova.eventmanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.viko.rkomaristova.eventmanagement.dao.EventDao;
import lt.viko.rkomaristova.eventmanagement.dto.EventDto;
import lt.viko.rkomaristova.eventmanagement.entities.AgendaPoint;
import lt.viko.rkomaristova.eventmanagement.entities.Event;

@Service
public class EventService {

	@Autowired
	private EventDao eventDao;

//	public void saveEvent(String description, LocalDate startDate, LocalDate endDate, String city, String name) {
//		Event event = new Event(description, startDate, endDate, city, name);
//		eventDao.saveEvent(event);
//	}

//	public void updateEvent(EventDto eventDto) {
//		Event event = eventDao.findEventById(eventDto.getId()).get(0);
//		mapDtoToEvent(event, eventDto);
//		eventDao.saveEvent(event);
//	}

	public void deleteEventById(Long id) {
		eventDao.deleteEventById(id);
	}

	public List<Event> getEventByName(String name) {
		return eventDao.findEventByName(name);
	}

	public List<Event> getAllEvents() {
		return eventDao.findAllEvents();
	}

	public Event getEventById(Long id) {
		List<Event> events = eventDao.findEventById(id);
		if (!events.isEmpty()) {
			return events.get(0);
		}
		return null;
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
