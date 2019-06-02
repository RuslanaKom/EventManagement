package lt.viko.rkomaristova.eventmanagement.dao;

import java.util.List;

import lt.viko.rkomaristova.eventmanagement.entities.User;
import lt.viko.rkomaristova.eventmanagement.entities.UserDetails;

public interface UserDao {
	
	User saveUser(User user);
	void saveUserDetails (UserDetails userDetails);
	
	List<User> findUsers();
	
	List<User> findUserById(Long id);

	UserDetails findUserDetailsByUserName(String userName);

	void deleteUserByLogin(String login);
	void deleteUserById(Long id);
}
