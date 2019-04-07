package lt.viko.rkomaristova.eventmanagement.dao;

import java.time.LocalDate;
import java.util.List;

import lt.viko.rkomaristova.eventmanagement.entities.AgendaPoint;
import lt.viko.rkomaristova.eventmanagement.entities.Event;

public interface EventDao {
	
	void saveEvent(Event event);
	void deleteEventById(Long id);
	
	List<Event> findEventByName(String name);
	List<Event> findEventById(Long id);
	List<Event> findAllEvents();
	List<Event> findEventByCriteria(String name, String city, String category, Boolean isFree, Boolean future);
	List<Event> findEventWithDetails(Long eventId);
	List<AgendaPoint> findAgendaByEvent(Event event);
	List<Event> findUserFavouriteEvents(Long userId);
}
