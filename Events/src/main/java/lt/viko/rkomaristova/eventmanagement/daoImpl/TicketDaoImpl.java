package lt.viko.rkomaristova.eventmanagement.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import lt.viko.rkomaristova.eventmanagement.dao.TicketDao;
import lt.viko.rkomaristova.eventmanagement.entities.Ticket;

@Repository
public class TicketDaoImpl implements TicketDao {
	
	private static final String FIND_TICKET_BY_USER_ID = "select t from Ticket t where t.user=(select u from User u where u.id=:userId)";
	@PersistenceContext
	private EntityManager em;
	
	public Ticket saveTicket(Ticket ticket) {
		em.persist(ticket);
		return ticket;
	}

	public List<Ticket> findTicketsByUserId(Long userId){
		Query query = em.createQuery(FIND_TICKET_BY_USER_ID);
		query.setParameter("userId", userId);
		return query.getResultList();
	}
}
