package com.res_application.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.res_application.model.E_WorkstationStatus;
import com.res_application.model.Reservation;
import com.res_application.model.User;
import com.res_application.model.Workstation;
import com.res_application.repository.JpaReservationRepository;
import com.res_application.repository.JpaUserRepository;
import com.res_application.repository.JpaWorkstationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReservationServices {

	// properties
		
	@Autowired private JpaReservationRepository repoReservation;
	
	@Autowired private JpaUserRepository repoUser;
	
	@Autowired private JpaWorkstationRepository repoWorkstation;
	
	@Autowired private WorkstationServices workstationService;
	
	@Autowired @Qualifier("fakeReservation")
	private ObjectProvider<Reservation> fakeRes;	
	
	// internal method
	
	public void createFakeReservation() {
		Reservation res = fakeRes.getObject();		
		User owner = repoUser.getRandomUser();
		Workstation ws = repoWorkstation.getRandomWorkstation();
		res.setOwner(owner);
		res.setLocation(ws);
		boolean toGoUser = checkUserReservation(res);
		boolean toGoWorkstat = checkWorkstationAvailability(res);
		if (toGoUser && toGoWorkstat) {
			repoReservation.save(res);
			log.info("Reservation correctly persisted on DB");
		} else {
			log.warn("Unable create a new reservation:");
			if (!toGoUser && toGoWorkstat) log.warn("User already has a reservation for that day"); 
			else if (!toGoWorkstat && toGoUser) log.warn("Workstation already occupied in that day");
			else log.warn("User already has a reservation for that day and workstation is already occupied!");
		}
	}
	
	public boolean checkUserReservation(Reservation r) {
		List<Reservation> userReservations = repoReservation.findByOwner(r.getOwner());
		userReservations = userReservations.stream().filter(res -> res.getDate().isEqual(r.getDate())).collect(Collectors.toList());
		if (userReservations.size() > 0) {
			return sameOrNot(userReservations, r);
		} else {
			return true;
		}
	}
	
	public boolean checkWorkstationAvailability(Reservation r) {
		List<Reservation> workstationReservations = repoReservation.findByLocation(r.getLocation());
		workstationReservations = workstationReservations.stream().filter(res -> res.getDate().isEqual(r.getDate())).collect(Collectors.toList());
		if (workstationReservations.size() > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean checkWorkstationAvailabilityUpdate(Reservation r) {
		List<Reservation> workstationReservations = repoReservation.findByLocation(r.getLocation());
		workstationReservations = workstationReservations.stream().filter(res -> res.getDate().isEqual(r.getDate())).collect(Collectors.toList());
		if (workstationReservations.size() > 0) {
			return sameOrNot(workstationReservations, r);
		} else {
			return true;
		}
	}
	
	public boolean sameOrNot(List<Reservation> resList, Reservation r) {
		if(resList.get(0).getId() == r.getId()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void updateWorkstationAvailability(Reservation r) {
		Workstation ws = r.getLocation();
		if (r.getDate().isEqual(LocalDate.now())) {
			ws.setStatus(E_WorkstationStatus.UNAVAILABLE);
			workstationService.updateWorkstation(ws);
		} else {
			ws.setStatus(E_WorkstationStatus.AVAILABLE);
			workstationService.updateWorkstation(ws);
		}
	}
	
	public void updateWorkstationAvailabilityOnDelete(Reservation r) {
		Workstation ws = r.getLocation();
		if (r.getDate().isEqual(LocalDate.now())) {
			ws.setStatus(E_WorkstationStatus.AVAILABLE);
			workstationService.updateWorkstation(ws);
		}
	}
	
	// Jpa methods
	
		public void persistReservation(Reservation r) {
			boolean toGoUser = checkUserReservation(r);
			boolean toGoWorkstat = checkWorkstationAvailability(r);
			if (toGoUser && toGoWorkstat) {
				repoReservation.save(r);
				log.info("Reservation correctly persisted on DB");
				updateWorkstationAvailability(r);
			} else {
				log.warn("Unable create a new reservation:");
				if (!toGoUser && toGoWorkstat) log.warn("User already has a reservation for that day"); 
				else if (!toGoWorkstat && toGoUser) log.warn("Workstation already occupied in that day");
				else log.warn("User already has a reservation for that day and workstation is already occupied!");
			}
		}
			
		public void updateReservation(Reservation r) {
			boolean toGoUser = checkUserReservation(r);
			boolean toGoWorkstat = checkWorkstationAvailabilityUpdate(r);
			if (toGoUser && toGoWorkstat) {
				repoReservation.save(r);
				log.info("Reservation correctly updated on DB");
				updateWorkstationAvailability(r);
			} else {
				log.warn("Unable update reservation:");
				if (!toGoUser && toGoWorkstat) log.warn("User already has a reservation for that day"); 
				else if (!toGoWorkstat && toGoUser) log.warn("Workstation already occupied in that day");
				else log.warn("User already has a reservation for that day and workstation is already occupied!");
			}
		}
			
		public void removeReservation(Reservation r) {
			repoReservation.delete(r);
			log.info("Reservation correctly removed from DB");
			updateWorkstationAvailabilityOnDelete(r);
		}
		
		public void removeReservation(Long id) {
			Reservation r = findReservationById(id);
			removeReservation(r);
		}

		public Reservation findReservationById(Long id) {
			return repoReservation.findById(id).get();
		}
		
		public List<Reservation> findAllReservation() {
			return (List<Reservation>) repoReservation.findAll();
		}
		
		public List<Reservation> findByUser(User u) {
			return repoReservation.findByOwner(u);
		}
	
}
