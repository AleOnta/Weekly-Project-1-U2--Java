package com.res_application.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.res_application.model.Workstation;
import com.res_application.repository.JpaWorkstationRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WorkstationServices {
	
	// properties
	
	@Autowired 
	private JpaWorkstationRepository repoWorkstation;
		
	// Jpa methods
		
	public void persistWorkstation(Workstation w) {
		repoWorkstation.save(w);
		log.info("Workstation correctly persisted on DB");
	}
		
	public void updateWorkstation(Workstation w) {
		repoWorkstation.save(w);
		log.info("Workstation correctly updated on DB");
	}
		
	public void removeWorkstation(Workstation w) {
		repoWorkstation.delete(w);
		log.info("Workstation correctly removed from DB");
	}
	
	public void removeWorkstation(Long id) {
		repoWorkstation.deleteById(id);
		log.info("Workstation correctly removed from DB");
	}

	public Workstation findWorkstationById(Long id) {
		return repoWorkstation.findById(id).get();
	}
	
	public List<Workstation> findAllWorkstations() {
		return (List<Workstation>) repoWorkstation.findAll();
	}

}
