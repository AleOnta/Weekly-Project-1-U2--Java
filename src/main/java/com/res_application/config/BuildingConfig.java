package com.res_application.config;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.github.javafaker.Faker;
import com.res_application.model.Building;

@Configuration
public class BuildingConfig {

	@Bean("customBuilding")
	@Scope("prototype")
	public Building customBuilding() {
		return new Building();
	}
	
	@Bean("fakeBuilding")
	@Scope("prototype")
	public Building fakeBuilding() {
		Faker fake = Faker.instance(new Locale("it-IT"));
		Building b = Building.builder()
					.id(null)
					.name(fake.university().name())
					.address(fake.address().streetName() + ", " + fake.address().streetAddressNumber())
					.city(fake.address().city())
					.build();
		return b;
	}
	
	@Bean("paramsBuilding")
	@Scope("prototype")
	public Building paramsBuilding(String name, String address, String city) {
		Building b = new Building(null, name, address, city);
		return b;
	}
	
}
