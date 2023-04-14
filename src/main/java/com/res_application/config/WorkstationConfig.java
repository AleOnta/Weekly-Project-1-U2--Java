package com.res_application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
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
	
	@Bean("paramsWorkstation")
	@Scope("prototype")
	public Workstation paramsWorkstation(String description, E_WorkstationType type, E_WorkstationStatus status, int max_sit, Building building) {
		Workstation ws = new Workstation(null, description, type, status, max_sit, building);
		return ws;
	}
	
}
