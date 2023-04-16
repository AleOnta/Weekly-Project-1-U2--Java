package com.res_application.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String lastname;
	
	private String email;
	
	private String username;
	
	@OneToMany(mappedBy = "owner", cascade = {CascadeType.MERGE, CascadeType.REFRESH}, orphanRemoval = true, fetch = FetchType.EAGER)
	private final List<Reservation> reservations = new ArrayList<Reservation>();

	private List<Long> getResIds() {
		List<Long> resId = new ArrayList<Long>();
		reservations.forEach(r -> resId.add(r.getId()));
		return resId;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastname=" + lastname + ", email=" + email + ", username="
				+ username + ", reservationsId's=" + getResIds() + "]";
	}
	
	
}
