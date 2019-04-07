package lt.viko.rkomaristova.eventmanagement.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import lt.viko.rkomaristova.eventmanagement.dao.FeedbackDao;
import lt.viko.rkomaristova.eventmanagement.entities.Feedback;

@Repository
public class FeedbackDaoImpl implements FeedbackDao {
	
	@PersistenceContext
	private EntityManager em;
	
	private final String FIND_FEEDBACK_BY_EVENT_ID = "select f from Feedback f where f.event=(select e from Event e where e.id=:eventId)";
	
	public void saveFeedback(Feedback feedback) {
		em.persist(feedback);
	}

	public List<Feedback> getFeedBackForEvent(Long eventId) {
		Query query = em.createQuery(FIND_FEEDBACK_BY_EVENT_ID);
		query.setParameter("eventId", eventId);
		return query.getResultList();
	}
}
