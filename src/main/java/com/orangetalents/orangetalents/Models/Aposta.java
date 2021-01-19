package com.orangetalents.orangetalents.Models;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "apostas")
public class Aposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	@OneToMany(mappedBy = "aposta",cascade = CascadeType.ALL)
	private Set<Numbers> betNumbers;

	public Set<Integer> getBetNumbers() {
		return betNumbers.stream().map( n -> n.getGeneratedNumber()).collect(Collectors.toSet());
	}
}