package com.res_application.services;

import java.util.List;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.res_application.model.Building;
import com.res_application.model.User;
import com.res_application.repository.JpaUserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServices {

	@Autowired private JpaUserRepository repoUser;
	
	@Autowired @Qualifier("fakeUser")
	private ObjectProvider<User> fakeUser;
	
	// internal methods

	public void createfakeUser() {
		persistUser(fakeUser.getObject());
	}
	
	// Jpa methods
	
	public void persistUser(User u) {
		repoUser.save(u);
		log.info("User correctly persisted on DB");
	}
	
	public void updateUser(User u) {
		repoUser.save(u);
		log.info("User correctly updated on DB");
	}
	
	public void removeUser(User u) {
		repoUser.delete(u);
		log.info("User correctly removed from DB");
	}
	
	public void removeUser(Long id) {
		repoUser.deleteById(id);
		log.info("User correctly removed from DB");
	}
	
	public User findUserById(Long id) {
		return repoUser.findById(id).get();
	}
	
	public List<User> findAllUser() {
		return (List<User>) repoUser.findAll();
	}
	
	
}
