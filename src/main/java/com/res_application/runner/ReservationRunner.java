package com.res_application.runner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.res_application.model.Building;
import com.res_application.model.E_WorkstationStatus;
import com.res_application.model.E_WorkstationType;
import com.res_application.model.Reservation;
import com.res_application.model.User;
import com.res_application.model.Workstation;
import com.res_application.services.BuildingServices;
import com.res_application.services.ReservationServices;
import com.res_application.services.UserServices;
import com.res_application.services.WorkstationServices;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReservationRunner implements CommandLineRunner {

	@Autowired
	private UserServices userService;
	
	@Autowired
	private BuildingServices buildingService;
	
	@Autowired
	private WorkstationServices workstationService;
	
	@Autowired
	private ReservationServices reservationService;
	
	@Autowired
	private ApplicationContext ctx;
	
	private Scanner sc = new Scanner(System.in);
	
	@Override
	public void run(String... args) throws Exception {
		
		// USERS SECTION
		/*
		// creating a user custom:
		User u1 = (User) ctx.getBean("customUser");
		u1.setName("Alessandro");
		u1.setLastname("Ontani");
		u1.setEmail(u1.getName()+"."+u1.getLastname()+"@example.it");
		u1.setUsername(u1.getName()+"_"+u1.getLastname());
		userService.persistUser(u1);
		
		// creating a user by params:
		User u2 = (User) ctx.getBean("paramsUser", "Giorgio", "Salemme");
		userService.persistUser(u2);
		
		// creating some fake users through JavaFaker Library and lombok @builder
		userService.createfakeUser();
		userService.createfakeUser();
		userService.createfakeUser();
		
		
		
		// BUILDINGS SECTION
		
		// creating a custom building
		Building b1 = (Building) ctx.getBean("customBuilding");
		b1.setName("Epicode Global");
		b1.setAddress("Via di Epicode Global, 25");
		b1.setCity("Roma");
		buildingService.persistBuilding(b1);
		
		// creating a building by params
		Building b2 = (Building) ctx.getBean("paramsBuilding", "Accademia di Firenze", "Via Don Sturzo, 54", "Firenze");
		buildingService.persistBuilding(b2);
		
		// creating a fake building through JavaFaker Library and lombok @builder
		buildingService.createfakeBuilding();
		
		
		// WORKSTATIONS SECTION
		
		// creating a custom workstation
		Workstation w1 = (Workstation) ctx.getBean("customWorkstation");
		w1.setBuilding(b1);
		w1.setDescription("new custom workstation by constructor");
		w1.setType(E_WorkstationType.HALL);
		w1.setMax_sit(80);
		workstationService.persistWorkstation(w1);
		
		// creating a workstation by params
		Workstation w2 = (Workstation) ctx.getBean("paramsWorkstation", "Perfect hall for hackathons", E_WorkstationType.HALL, E_WorkstationStatus.AVAILABLE, 85, b2);
		workstationService.persistWorkstation(w2);
		
		// creating some fake workstations through JavaFaker Library and lombok @builder
		workstationService.createFakeWorkstation();
		workstationService.createFakeWorkstation();
		workstationService.createFakeWorkstation();
		workstationService.createFakeWorkstation();
		workstationService.createFakeWorkstation();
		
		
		// RESERVATIONS SECTION
		
		// creating a custom reservation
		Reservation r1 = (Reservation) ctx.getBean("customReservation");
		r1.setOwner(u1);
		r1.setLocation(w2);
		r1.setDate(LocalDate.now());
		reservationService.persistReservation(r1);
		
		// creating a reservation by params
		Reservation r2 = (Reservation) ctx.getBean("paramsReservation", u1, w1, LocalDate.of(2023, 04, 29));
		reservationService.persistReservation(r2);
		
		// creating some fake reservations through JavaFaker Library and lombok @builder
		reservationService.createFakeReservation();
		reservationService.createFakeReservation();
		reservationService.createFakeReservation();
		reservationService.createFakeReservation();
		reservationService.createFakeReservation();
		reservationService.createFakeReservation();
		
		
		Reservation r = reservationService.findReservationById(1l);
		reservationService.removeReservation(r);
		*/
		
		log.info("Launching app...");
		log.info("Welcome into the system, please select a user by the ID to proceed:\n");
		
		List<User> userList = userService.findAllUser();
		userList.forEach(u -> log.info(u.toString()));
		
		User userPicked = userService.findUserById((long)askId(userList.size())); 
		
		log.info("Perfect! Now select the type of operation you would like to perform by the corresponding number:");
		
		boolean isRunning = true;
		while (isRunning) {
			log.info("0 - to Exit | 1 - Print all reservations at your name | 2 - Search workstations by City | 3 - Seach workstations by Type | 4 - Add a new Reservation");
			int operation = askId(5);
			switch (operation) {
			case 0 -> {
				log.info("okay then, shutting off the system");
				log.info("application built by Ontani Alessandro");
				isRunning = false;
				break;
			}
			case 1 -> {
				log.info("Okay, here's a list of all your reservations:");
				List<Reservation> userRes = reservationService.findByUser(userPicked);
				if(userRes.size() > 0) {
					userRes.forEach(r -> log.info(r.toString()));
				} else {
					log.info("No reservations were found for current user");
				}
			}
			case 2 -> {
				log.warn("--->the next log.info is made just to let the user now what city are available at the moment in the system<---");
				log.info("Okay, here is a list of the available city:");
				List<Building> allBuilds = buildingService.findAllBuilding();
				for (int i = 0; i < allBuilds.size(); i++) {
					log.info((i+1) + " - " + allBuilds.get(i).getCity());
				}
				
				log.info("Select the city you're interested in by the corresponding number:");
				Integer cityPick = askId(allBuilds.size()+1);
				List<Workstation> worksList = workstationService.findWorkstationsByCity(allBuilds.get(cityPick-1).getCity());
				
				log.info("Here's the list of workstations presents in the selected city:");
				worksList.forEach(w -> log.info(w.toString()));
			}
			case 3 -> {
				log.info("Okay then, select by the corresponding number the type of workstation you're looking for:");
				log.info("1 - Private | 2 - Hall | 3 - Meeting | 4 - Openspace");
				
				List<Workstation> worksList = new ArrayList<Workstation>();
				switch(askId(5)) {
				case 1 -> worksList = workstationService.findWorkstationsByType(E_WorkstationType.PRIVATE);
				case 2 -> worksList = workstationService.findWorkstationsByType(E_WorkstationType.HALL);
				case 3 -> worksList = workstationService.findWorkstationsByType(E_WorkstationType.MEETINGS);
				case 4 -> worksList = workstationService.findWorkstationsByType(E_WorkstationType.OPENSPACE);
				}
				
				log.info("Here's the list of all workstations with the selected type:");
				worksList.forEach(w -> log.info(w.toString()));
			}
			case 4 -> {
				log.info("Okay, let's create a new reservations");
				log.info("Let's start from the Workstation you need to book, how would you like to search for it?");
				log.info("1 - Print all workstation | 2 - Search by type | 3 - Search by city");
				Integer typeOfSearch = askId(4);
				
				switch (typeOfSearch) {
				case 1 -> {
					
					List<Workstation> allWorks = workstationService.findAllWorkstations();
					allWorks.forEach(w -> log.info(w.toString()));
					log.info("Select a workstation by it's corresponding ID:");
					
					Workstation w = workstationService.findWorkstationById((long) askId(50));
					
					log.info("Now insert the date of your reservation, read next log to see the pattern");
					log.warn("Insert the date as yyyy-mm-dd (remember to use the dash)");
					
					LocalDate resDate = askDate();
					
					Reservation res = (Reservation) ctx.getBean("paramsReservation", userPicked, w, resDate);
					reservationService.persistReservation(res);
				}
				case 2 -> {
					log.info("Okay then, select by the corresponding number the type of workstation you're looking for:");
					log.info("1 - Private | 2 - Hall | 3 - Meeting | 4 - Openspace");
					
					List<Workstation> worksList = new ArrayList<Workstation>();
					switch(askId(5)) {
					case 1 -> worksList = workstationService.findWorkstationsByType(E_WorkstationType.PRIVATE);
					case 2 -> worksList = workstationService.findWorkstationsByType(E_WorkstationType.HALL);
					case 3 -> worksList = workstationService.findWorkstationsByType(E_WorkstationType.MEETINGS);
					case 4 -> worksList = workstationService.findWorkstationsByType(E_WorkstationType.OPENSPACE);
					}
					log.info("Here's the list of all workstations with the selected type:");
					worksList.forEach(w -> log.info(w.toString()));
					log.info("Select a workstation by it's corresponding ID:");
					
					Workstation w = workstationService.findWorkstationById((long) askId(50));
					
					log.info("Now insert the date of your reservation, read next log to see the pattern");
					log.warn("Insert the date as yyyy-mm-dd (remember to use the dash)");
					
					LocalDate resDate = askDate();
					
					Reservation res = (Reservation) ctx.getBean("paramsReservation", userPicked, w, resDate);
					reservationService.persistReservation(res);
				}
				case 3 -> {
					log.info("Okay, here is a list of the available city:");
					List<Building> allBuilds = buildingService.findAllBuilding();
					for (int i = 0; i < allBuilds.size(); i++) {
						log.info((i+1) + " - " + allBuilds.get(i).getCity());
					}
					
					log.info("Select the city you're interested in by the corresponding number:");
					Integer cityPick = askId(allBuilds.size()+1);
					List<Workstation> worksList = workstationService.findWorkstationsByCity(allBuilds.get(cityPick-1).getCity());
					
					log.info("Here's the list of workstations presents in the selected city:");
					worksList.forEach(w -> log.info(w.toString()));
					
					log.info("Select a workstation by it's corresponding ID:");
					
					Workstation w = workstationService.findWorkstationById((long) askId(50));
					
					log.info("Now insert the date of your reservation, read next log to see the pattern");
					log.warn("Insert the date as yyyy-mm-dd (remember to use the dash)");
					
					LocalDate resDate = askDate();
					
					Reservation res = (Reservation) ctx.getBean("paramsReservation", userPicked, w, resDate);
					reservationService.persistReservation(res);
				}
				
				}
			}
			}
		}
	}
	
	public int askId(int max) {
		boolean isRunning = true;
		int pick = 0;
		while(isRunning) {
			pick = sc.nextInt();
			if (pick >= 0 && pick < max) {
				break;
			} else {
				log.warn("Invalid user ID");
				log.info("Insert again the user ID");
				continue;
			}
		}
		return pick;
	}
	
	public LocalDate askDate() {
		String dateInString = sc.next() + sc.nextLine();
		String[] dateInArray = dateInString.split("-");
		int year = Integer.parseInt(dateInArray[0]);
		int month = Integer.parseInt(dateInArray[1]);
		int day = Integer.parseInt(dateInArray[2]);
		return LocalDate.of(year, month, day);
	}

}
