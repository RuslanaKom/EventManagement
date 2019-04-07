package lt.viko.rkomaristova.eventmanagement.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private String city;
	private Boolean isFree;
	private Double basicPrice;
	private String category;
	
	@ManyToMany
	private Set <User> organisators;
	
	@ManyToMany
	private Set<Organization> sponsors;

	private String name;
	
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

	public boolean getIsFree() {
		return isFree;
	}

	public void setisFree(boolean isFree) {
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

	public Long getId() {
		return id;
	}

	
}
