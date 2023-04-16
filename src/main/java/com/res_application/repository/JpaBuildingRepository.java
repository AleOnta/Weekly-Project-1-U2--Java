package com.res_application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.res_application.model.Building;

@Repository
public interface JpaBuildingRepository extends CrudRepository<Building, Long> {

	// finders
	public Building findByName(String name);
	
	public Building findByAddress(String address);
	
	public List<Building> findByCity(String city);
	
	// complex finders
	
	
	// custom queries
	
	@Query(value = "SELECT b FROM Building b ORDER BY random() LIMIT 1")
	public Building getRandomBuilding();
	
	@Query(value = "SELECT COUNT(b) FROM Building b")
	public Integer countHowManyBuilding();
}
