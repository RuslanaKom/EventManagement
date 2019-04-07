package lt.viko.rkomaristova.eventmanagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Event event;
	
	private Double price;

	@ManyToOne
	private User user;
	
	public Ticket() {
		super();
	}

	public Ticket(Event event, Double price, User user) {
		super();
		this.event = event;
		this.price = price;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
