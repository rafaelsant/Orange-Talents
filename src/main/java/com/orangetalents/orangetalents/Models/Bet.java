package com.orangetalents.orangetalents.Models;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "bet")
public class Bet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Email
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "bet",cascade = CascadeType.ALL)
	private Set<Numbers> betNumbers;

	public Bet() {
	}

	public Set<Integer> getBetNumbers() {
		return betNumbers.stream()
				.map( n -> n.getGeneratedNumber())
				.collect(Collectors.toSet());
	}

	public String getEmail() {
		return email;
	}

	public Bet(Long id, @NotNull @Email String email, Set<Numbers> betNumbers) {
		this.id = id;
		this.email = email;
		this.betNumbers = betNumbers;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBetNumbers(Set<Numbers> betNumbers) {
		this.betNumbers = betNumbers;
	}
	
}
