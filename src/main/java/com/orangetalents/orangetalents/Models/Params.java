package com.orangetalents.orangetalents.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Params {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer quantity;
	
	private Integer ranges;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getRange() {
		return ranges;
	}

	public void setRange(Integer range) {
		this.ranges = range;
	}

	

	public Params(Integer quantity, Integer range) {
		super();
		this.quantity = quantity;
		this.ranges = range;
	}

	public Params() {
		super();
	}
	
}
