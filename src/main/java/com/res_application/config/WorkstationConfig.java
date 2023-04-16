package com.res_application.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.javafaker.Faker;
import com.res_application.model.Building;
import com.res_application.model.E_WorkstationStatus;
import com.res_application.model.E_WorkstationType;
import com.res_application.model.Workstation;

@Configuration
public class WorkstationConfig {

	@Bean("customWorkstation")
	@Scope("prototype")
	public Workstation customWorkstation() {
		return new Workstation();
	}
	
	@Bean("fakeWorkstation") 
	@Scope("prototype") 
	public Workstation fakeWorkstation() {
		Faker fake = Faker.instance(new Locale("it-IT"));
		Integer random = fake.number().numberBetween(10, 200);
		E_WorkstationType type = null;
		
		 if(random <= 50) {
			 type = E_WorkstationType.PRIVATE;
		 } else if (random > 50 && random <= 100) {
			 type = E_WorkstationType.HALL;
		 } else if (random > 100 && random <= 150) {
			 type = E_WorkstationType.MEETINGS;
		 } else {
			 type = E_WorkstationType.OPENSPACE;
		 }
		
		Workstation ws = 
				Workstation.builder()
				.id(null)
				.description(fake.lorem().characters(25))
				.type(type)
				.status(E_WorkstationStatus.AVAILABLE)
				.max_sit(random)
				.build();
		return ws;
	}
	
	@Bean("paramsWorkstation")
	@Scope("prototype")
	public Workstation paramsWorkstation(String description, E_WorkstationType type, E_WorkstationStatus status, int max_sit, Building building) {
		Workstation ws = new Workstation(null, description, type, status, max_sit, building);
		return ws;
	}
	
}
