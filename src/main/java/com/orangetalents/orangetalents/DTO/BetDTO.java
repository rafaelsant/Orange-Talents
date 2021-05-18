package com.orangetalents.orangetalents.DTO;

import java.util.Set;

public class BetDTO {
	
	private String email;
	private Set<Integer> numerosGerados;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Integer> getNumerosGerados() {
		return numerosGerados;
	}
	public void setNumerosGerados(Set<Integer> numerosGerados) {
		this.numerosGerados = numerosGerados;
	}
	public BetDTO() {
		super();
	}
	public BetDTO(String email, Set<Integer> numerosGerados) {
		super();
		this.email = email;
		this.numerosGerados = numerosGerados;
	}
	 
	

}
