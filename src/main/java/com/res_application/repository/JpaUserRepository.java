package com.res_application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.res_application.model.Building;
import com.res_application.model.User;

@Repository
public interface JpaUserRepository extends CrudRepository<User, Long> {

	// finders
	public User findByName(String name);
	
	public User findByLastname(String lastname);
	
	public User findByUsername(String username);
	
	public User findByEmail(String email);
	
	// complex finders
	
	
	// custom queries
	
		@Query(value = "SELECT u FROM User u ORDER BY random() LIMIT 1")
		public User getRandomUser();
		
		@Query(value = "SELECT COUNT(u) FROM User u")
		public Integer countHowManyUsers();
}
