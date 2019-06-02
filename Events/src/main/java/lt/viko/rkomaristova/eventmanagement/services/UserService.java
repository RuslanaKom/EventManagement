package lt.viko.rkomaristova.eventmanagement.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UserService implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public boolean saveNewUser(UserRegistrationDto userDto) {
		try {
		userDao.findUserDetailsByUserName(userDto.getUsername());
		}
		catch(NoResultException e){
		User user = new User(userDto.getFirstName(), userDto.getLastName(), userDto.getPhone(), userDto.getEmail());
		User savedUser = userDao.saveUser(user);
		UserDetails userDetails = new UserDetails(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()));
		userDetails.setRole(Roles.PARTICIPANT.toString());
		userDetails.setUser(savedUser);
		userDao.saveUserDetails(userDetails);
		return  true;
		}
		return false;
	}
	
	public List<User> findAllUsers(){
		return userDao.findUsers();
	}
	
	public List<User> findUserById(Long id){
	return userDao.findUserById(id);
	}
	
	public User checkLogin(String username, String password) {
		if(username==null) {
			return null;
		}
		UserDetails userDetails = null;
		try {
			userDetails = userDao.findUserDetailsByUserName(username);
		} catch (NoResultException e) {
			return null;
		}
		if(passwordEncoder.matches(password, userDetails.getPassword())){
			return userDetails.getUser();
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

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		try {
			return userDao.findUserDetailsByUserName(username);
		} catch (NoResultException e) {
			throw new UsernameNotFoundException(String.format("Username [%s] not found", username));
		}
	}
}
