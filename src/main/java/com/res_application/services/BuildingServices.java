package com.res_application.services;

import java.util.List;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.res_application.model.Building;
import com.res_application.repository.JpaBuildingRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BuildingServices {
	
	// properties
	
	@Autowired 
	private JpaBuildingRepository repoBuilding;
	
	@Autowired @Qualifier("fakeBuilding")
	private ObjectProvider<Building> fakeBuilding;
	
	// internal methods
	
	public void createfakeBuilding() {
		persistBuilding(fakeBuilding.getObject());
	}
	
	// Jpa methods
	
	public void persistBuilding(Building b) {
		repoBuilding.save(b);
		log.info("Building correctly persisted on DB");
	}
	
	public void updateBuilding(Building b) {
		repoBuilding.save(b);
		log.info("Building correctly updated on DB");
	}
	
	public void removeBuilding(Building b) {
		repoBuilding.delete(b);
		log.info("Building correctly removed from DB");
	}
	
	public void removeBuilding(Long id) {
		repoBuilding.deleteById(id);
		log.info("Building correctly removed from DB");
	}
	
	public Building findById(Long id) {
		return repoBuilding.findById(id).get();
	}
	
	public List<Building> findAllBuilding() {
		return (List<Building>) repoBuilding.findAll();
	}
}
