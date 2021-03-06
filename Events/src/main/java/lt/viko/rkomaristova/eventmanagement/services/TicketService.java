package lt.viko.rkomaristova.eventmanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.viko.rkomaristova.eventmanagement.dao.TicketDao;
import lt.viko.rkomaristova.eventmanagement.entities.Event;
import lt.viko.rkomaristova.eventmanagement.entities.Ticket;
import lt.viko.rkomaristova.eventmanagement.entities.User;

@Service
public class TicketService {

	@Autowired
	TicketDao ticketDao;
	
	@Transactional
	public Ticket buyTicket(Event event, User user) {
		Ticket ticket = new Ticket(event, event.getBasicPrice(),user);
		return ticketDao.saveTicket(ticket);
	}

	public List<Ticket> getTicketsByUser(Long userId){
		return ticketDao.findTicketsByUserId(userId);
	}
	
}
