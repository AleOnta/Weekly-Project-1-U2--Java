package com.res_application.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "workstations")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Workstation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	
	@Enumerated(EnumType.STRING)
	private E_WorkstationType type;
	
	@Enumerated(EnumType.STRING)
	private E_WorkstationStatus status;
	
	private Integer max_sit;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "building_id")
	private Building building;
	
	@OneToMany(mappedBy = "location", cascade = {CascadeType.MERGE, CascadeType.REFRESH}, orphanRemoval = true)
	private final List<Reservation> reservations = new ArrayList<Reservation>();

	@Override
	public String toString() {
		return "Workstation [id=" + id + ", description=" + description + ", type=" + type + ", status=" + status
				+ ", max_sit=" + max_sit + ", building=" + building.getId() + "]";
	}
	
	
}
