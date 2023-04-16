package com.res_application.config;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.javafaker.Faker;
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
	
	@Bean("fakeReservation")
	@Scope("prototype")
	public Reservation fakeReservation() {
		Faker fake = Faker.instance(new Locale("it-IT"));
		Reservation res = Reservation.builder()
				.id(null)
				.owner(null)
				.location(null)
				.date(LocalDate.of(2023, 04, fake.number().numberBetween(1, 30)))
				.build();
		return res;
	}
	
	@Bean("paramsReservation")
	@Scope("prototype")
	public Reservation paramsReservation(User u, Workstation w, LocalDate res_date) {
		return new Reservation(null, u, w, res_date);
	}
	
}
