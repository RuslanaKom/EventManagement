package lt.viko.rkomaristova.eventmanagement.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import lt.viko.rkomaristova.eventmanagement.entities.AgendaPoint;
import lt.viko.rkomaristova.eventmanagement.entities.Event;
import lt.viko.rkomaristova.eventmanagement.entities.Organization;
import lt.viko.rkomaristova.eventmanagement.entities.User;

public class EventDto {
	
	private Long id;
	private String name;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private String city;
	private Boolean isFree;
	private Double basicPrice;
	private String category;
	private List <AgendaPoint> agenda;
	private Set <User> organisators;
	private Set <Organization> sponsors;
	
	public EventDto(Event event, List<AgendaPoint> agenda){
		super();
		this.id = event.getId();
		this.name = event.getName();
		this.description = event.getDescription();
		this.startDate = event.getStartDate();
		this.endDate = event.getEndDate();
		this.city = event.getCity();
		this.isFree = event.getIsFree();
		this.basicPrice = event.getBasicPrice();
		this.category = event.getCategory();
		this.agenda = agenda;
		this.organisators = event.getOrganisators();
		this.sponsors = event.getSponsors();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Boolean getIsFree() {
		return isFree;
	}

	public void setIsFree(Boolean isFree) {
		this.isFree = isFree;
	}

	public Double getBasicPrice() {
		return basicPrice;
	}

	public void setBasicPrice(Double basicPrice) {
		this.basicPrice = basicPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<AgendaPoint> getAgenda() {
		return agenda;
	}

	public void setAgenda(List<AgendaPoint> agenda) {
		this.agenda = agenda;
	}

	public Set<User> getOrganisators() {
		return organisators;
	}

	public void setOrganisators(Set<User> organisators) {
		this.organisators = organisators;
	}

	public Set<Organization> getSponsors() {
		return sponsors;
	}

	public void setSponsors(Set<Organization> sponsors) {
		this.sponsors = sponsors;
	}

	
}
