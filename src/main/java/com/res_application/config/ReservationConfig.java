package com.res_application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.res_application.model.Reservation;
import com.res_application.model.User;
import com.res_application.model.Workstation;

@Configuration
public class ReservationConfig {

	@Bean("customReservation")
	@Scope("prototype")
	public Reservation customReservation() {
		return new Reservation();
	}
	
	@Bean("paramsReservation")
	@Scope("prototype")
	public Reservation paramsReservation(User u, Workstation w) {
		return new Reservation(null, u, w);
	}
	
}
