package lt.viko.rkomaristova.eventmanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.viko.rkomaristova.eventmanagement.dao.UserDao;
import lt.viko.rkomaristova.eventmanagement.dto.UserRegistrationDto;
import lt.viko.rkomaristova.eventmanagement.entities.Event;
import lt.viko.rkomaristova.eventmanagement.entities.User;
import lt.viko.rkomaristova.eventmanagement.entities.UserDetails;
import lt.viko.rkomaristova.eventmanagement.enums.Roles;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public boolean saveNewUser(UserRegistrationDto userDto) {
		if (!userDao.findUserDetailsByUserName(userDto.getUsername()).isEmpty()) {
			return false;
		}
		User user = new User(userDto.getFirstName(), userDto.getLastName(), userDto.getPhone(), userDto.getEmail());
		User savedUser = userDao.saveUser(user);
		UserDetails userDetails = new UserDetails(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()));
		userDetails.setRole(Roles.PARTICIPANT.toString());
		userDetails.setUser(savedUser);
		userDao.saveUserDetails(userDetails);
		return  true;
	}
	
	public List<User> findAllUsers(){
		return userDao.findUsers();
	}
	
	public List<User> findUserById(Long id){
	return userDao.findUserById(id);
	}
	
	public User checkLogin(String username, String password) {
		List<UserDetails> userDetails = userDao.findUserDetailsByUserName(username);
		if(username==null || userDetails.isEmpty()) {
			return null;
		}
		if(passwordEncoder.matches(password, userDetails.get(0).getPassword())){
			return userDetails.get(0).getUser();
		}
		return null;
	}
	
	@Transactional
	public void addEventToFavourites(User user, Event event){
		user.getFavouriteEvents().add(event);
		userDao.saveUser(user);
	}
	
	@Transactional
	public void removeEventFromFavourites(User user, Event event) {
		user.getFavouriteEvents().remove(event);
		userDao.saveUser(user);
	}
}
