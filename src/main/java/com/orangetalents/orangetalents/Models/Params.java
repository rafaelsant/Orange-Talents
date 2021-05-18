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
	
	private Integer BET_MAX_QUANTITY;
	
	private Integer BET_MAX_NUMBER;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Params(Integer bET_MAX_QUANTITY, Integer bET_MAX_NUMBER) {
		super();
		BET_MAX_QUANTITY = bET_MAX_QUANTITY;
		BET_MAX_NUMBER = bET_MAX_NUMBER;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((BET_MAX_NUMBER == null) ? 0 : BET_MAX_NUMBER.hashCode());
		result = prime * result + ((BET_MAX_QUANTITY == null) ? 0 : BET_MAX_QUANTITY.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Params other = (Params) obj;
		if (BET_MAX_NUMBER == null) {
			if (other.BET_MAX_NUMBER != null)
				return false;
		} else if (!BET_MAX_NUMBER.equals(other.BET_MAX_NUMBER))
			return false;
		if (BET_MAX_QUANTITY == null) {
			if (other.BET_MAX_QUANTITY != null)
				return false;
		} else if (!BET_MAX_QUANTITY.equals(other.BET_MAX_QUANTITY))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getBET_MAX_QUANTITY() {
		return BET_MAX_QUANTITY;
	}

	public void setBET_MAX_QUANTITY(Integer bET_MAX_QUANTITY) {
		BET_MAX_QUANTITY = bET_MAX_QUANTITY;
	}

	public Integer getBET_MAX_NUMBER() {
		return BET_MAX_NUMBER;
	}

	public void setBET_MAX_NUMBER(Integer bET_MAX_NUMBER) {
		BET_MAX_NUMBER = bET_MAX_NUMBER;
	}

	public Params() {
		super();
	}
	
}
