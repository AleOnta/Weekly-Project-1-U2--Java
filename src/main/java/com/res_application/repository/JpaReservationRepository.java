package com.res_application.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.res_application.model.Reservation;
import com.res_application.model.User;
import com.res_application.model.Workstation;

@Repository
public interface JpaReservationRepository extends CrudRepository<Reservation, Long> {

	// finders 
	
	public List<Reservation> findByLocation(Workstation location);
	
	public List<Reservation> findByDate(LocalDate date);
	
	public List<Reservation> findByOwner(User owner);
	
}
