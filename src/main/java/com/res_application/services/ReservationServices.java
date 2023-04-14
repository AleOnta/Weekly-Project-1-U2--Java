package com.res_application.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.res_application.model.Reservation;
import com.res_application.repository.JpaReservationRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReservationServices {

	// properties
	
	@Autowired private JpaReservationRepository repoReservation;
	
	// Jpa methods
	
		public void persistReservation(Reservation w) {
			repoReservation.save(w);
			log.info("Reservation correctly persisted on DB");
		}
			
		public void updateReservation(Reservation w) {
			repoReservation.save(w);
			log.info("Reservation correctly updated on DB");
		}
			
		public void removeReservation(Reservation r) {
			repoReservation.delete(r);
			log.info("Reservation correctly removed from DB");
		}
		
		public void removeReservation(Long id) {
			repoReservation.deleteById(id);
			log.info("Reservation correctly removed from DB");
		}

		public Reservation findReservationById(Long id) {
			return repoReservation.findById(id).get();
		}
		
		public List<Reservation> findAllReservation() {
			return (List<Reservation>) repoReservation.findAll();
		}
	
}
