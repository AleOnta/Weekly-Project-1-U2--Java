package com.res_application.config;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.github.javafaker.Faker;
import com.res_application.model.User;

@Configuration
public class UserConfig {

	@Bean("customUser")
	@Scope("prototype")
	public User customUser() {
		return new User();
	}
	
	@Bean("fakeUser")
	@Scope("prototype")
	public User fakeUser() {
		Faker fake = Faker.instance(new Locale("it-IT"));
		User u = User.builder()
				.name(fake.name().firstName())
				.lastname(fake.name().lastName())
				.build();
		return u;
	}

	@Bean("paramsUser")
	@Scope("prototype")
	public User paramsUser(String name, String lastname) {
		return new User(null, name, lastname, name+"."+lastname+"@example.com");
	}
}
