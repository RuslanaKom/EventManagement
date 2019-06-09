package lt.viko.rkomaristova.eventmanagement.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import lt.viko.rkomaristova.eventmanagement.dao.UserDao;
import lt.viko.rkomaristova.eventmanagement.entities.User;
import lt.viko.rkomaristova.eventmanagement.entities.UserDetails;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;
	
	private final String FIND_USERS = "select u from User u";
	private final String FIND_USER_BY_ID = "select u from User u where u.id=:id";
	private final String FIND_USERDETAILS_BY_USERNAME = "select ud from UserDetails ud where ud.username=:username";
	private final String FIND_USER_BY_USERNAME = "select ud.user from UserDetails ud where ud.username=:username";
	
	@Override
	public User saveUser(User user) {
		em.persist(user);
		return user;
	}
	
	@Override
	public void saveUserDetails(UserDetails userDetails) {
		em.persist(userDetails);
	}
	
	@Override
	public List<User> findUsers(){
		Query query = em.createQuery(FIND_USERS);
		return query.getResultList();
	}

	@Override
	public List<User> findUserById(Long id) {
		Query query = em.createQuery(FIND_USER_BY_ID);
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	@Override
	public UserDetails findUserDetailsByUserName(String username) {
		Query query = em.createQuery(FIND_USERDETAILS_BY_USERNAME);
		query.setParameter("username", username);
		return (UserDetails) query.getSingleResult();
	}

	@Override
	public User findUserByUserName(String username) {
		Query query = em.createQuery(FIND_USER_BY_USERNAME);
		query.setParameter("username", username);
		return (User) query.getSingleResult();
	}
}
