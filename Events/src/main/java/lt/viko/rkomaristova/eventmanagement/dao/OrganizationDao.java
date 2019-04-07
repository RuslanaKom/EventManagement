package lt.viko.rkomaristova.eventmanagement.dao;

import java.util.List;

import lt.viko.rkomaristova.eventmanagement.entities.Organization;

public interface OrganizationDao {
	
	void saveOrganization(Organization organization);
	
	void deleteOrganizationById(Long id);
	
	List<Organization> findOrganizationById(Long id);
	
	List<Organization> findOrganizationByName(String name);

}
