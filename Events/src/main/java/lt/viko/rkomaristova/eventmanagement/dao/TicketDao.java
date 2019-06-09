package lt.viko.rkomaristova.eventmanagement.dao;

import java.util.List;

import lt.viko.rkomaristova.eventmanagement.entities.Ticket;

public interface TicketDao {
	
	Ticket saveTicket(Ticket ticket);
	List<Ticket> findTicketsByUserId(String username);
}
