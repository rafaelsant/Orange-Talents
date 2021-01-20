package com.orangetalents.orangetalents.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;



@Table(name = "numbers")
@Entity
public class Numbers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer generatedNumber;
    
    @ManyToOne
    @JoinColumn(name = "aposta_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Aposta aposta;
    
	public Numbers(Integer generatedNumber, Aposta aposta) {
		this.generatedNumber = generatedNumber;
		this.aposta = aposta;
	}
	public Numbers() {};
    public Integer getGeneratedNumber() {
        return generatedNumber;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setGeneratedNumber(Integer generatedNumber) {
        this.generatedNumber = generatedNumber;
    }
    public void setAposta(Aposta aposta) {
        this.aposta = aposta;
    }
}
