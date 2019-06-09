package lt.viko.rkomaristova.eventmanagement.daoImpl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import lt.viko.rkomaristova.eventmanagement.dao.EventDao;
import lt.viko.rkomaristova.eventmanagement.entities.AgendaPoint;
import lt.viko.rkomaristova.eventmanagement.entities.Event;


@Repository
public class EventDaoImpl implements EventDao {

	@PersistenceContext
	private EntityManager em;
	
	private final String FIND_ALL_EVENTS = "select e from Event e";
	private final String FIND_EVENT_BY_ID = "select e from Event e where e.id=:id";
	private final String FIND_EVENT_WITH_DETAIL = "select e from Event e LEFT JOIN FETCH e.sponsors s where e.id=:eventId";
	private final String FIND_AGENDA_BY_EVENT="select a from AgendaPoint a where a.event=:event";
	//private final String FIND_EVENTS_BY_USER="select u.favouriteEvents from User u  where u.id=:userId";
	private final String FIND_EVENTS_BY_USERNAME="select ud.user.favouriteEvents from UserDetails ud where ud.username =:username";
	public void saveEvent(Event event) {
		// TODO Auto-generated method stub
	}

	public void deleteEventById(Long id) {
		// TODO Auto-generated method stub	
	}

	public List<Event> findEventByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Event> findEventById(Long id) {
		Query query = em.createQuery(FIND_EVENT_BY_ID);
		query.setParameter("id", id);
		return query.getResultList();
	}

	public List<Event> findAllEvents() {
		Query query = em.createQuery(FIND_ALL_EVENTS);
		return query.getResultList();
	}

	public List<Event> findEventByCriteria(String name, String city, String category, Boolean isFree, Boolean future) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Event> cq = cb.createQuery(Event.class);

		Root<Event> event = cq.from(Event.class);
		List<Predicate> predicates = new ArrayList<>();

		if (StringUtils.isNotEmpty(name)) {
			predicates.add(cb.like(event.get("name"), "%" + name + "%"));
		}
		if (StringUtils.isNotEmpty(city)) {
			predicates.add(cb.equal(event.get("city"), city));
		}
		if (StringUtils.isNotEmpty(category)) {
			predicates.add(cb.equal(event.get("category"), category));
		}
		if (isFree != null) {
			predicates.add(cb.equal(event.get("isFree"), isFree));
		}
		if (future != null) {
			if (future) {
			predicates.add(cb.greaterThan(event.get("startDate").as(LocalDate.class), LocalDate.now()));
			}
			else {
				predicates.add(cb.lessThan(event.get("startDate").as(LocalDate.class), LocalDate.now()));
			}
		}
		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		return em.createQuery(cq).getResultList();
	}

	@Override
	public List<Event> findEventWithDetails(Long eventId) {
		Query query = em.createQuery(FIND_EVENT_WITH_DETAIL);
		query.setParameter("eventId", eventId);
		return query.getResultList();
	}
	
	@Override
	public List<AgendaPoint> findAgendaByEvent(Event event) {
		Query query = em.createQuery(FIND_AGENDA_BY_EVENT);
		query.setParameter("event", event);
		return query.getResultList();
	}

	@Override
	public List<Event> findUserFavouriteEvents (String username) {
		Query query = em.createQuery(FIND_EVENTS_BY_USERNAME);
		query.setParameter("username", username);
		return query.getResultList();
	}
	
//	@Override
//	public List<Event> findUserFavouriteEvents (Long userId) {
//		Query query = em.createQuery(FIND_EVENTS_BY_USER);
//		query.setParameter("userId", userId);
//		return query.getResultList();
//	}
	
}
