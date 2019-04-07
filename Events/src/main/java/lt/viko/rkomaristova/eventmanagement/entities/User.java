package lt.viko.rkomaristova.eventmanagement.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	
	@ManyToOne
	private Organization organisation;
	
	@ManyToMany
	private Set<Event> favouriteEvents;

	public User() {
		favouriteEvents= new HashSet<>();
	}
	
	public User(String firstName, String lastName, String phone, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		favouriteEvents= new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Organization getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organization organisation) {
		this.organisation = organisation;
	}

	public Set<Event> getFavouriteEvents() {
		return favouriteEvents;
	}

	public void setFavouriteEvents(Set<Event> favouriteEvents) {
		this.favouriteEvents = favouriteEvents;
	}

}
