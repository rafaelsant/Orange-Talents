package com.orangetalents.orangetalents.Models;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "bet")
public class Bet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Email
	private String email;

	@OneToMany(mappedBy = "bet",cascade = CascadeType.ALL)
	private Set<Numbers> betNumbers;

	public Set<Integer> getBetNumbers() {
		return betNumbers.stream()
				.map( n -> n.getGeneratedNumber())
				.collect(Collectors.toSet());
	}
}
