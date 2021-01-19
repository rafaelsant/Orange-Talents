package com.orangetalents.orangetalents.Controller;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orangetalents.orangetalents.Models.Aposta;
import com.orangetalents.orangetalents.Services.ApostaService;

@RestController
@RequestMapping("api/v1/aposta")
public class ApostaController {
	@Autowired
	private ApostaService service;
	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping
	public List<Aposta> getApostas(){
		return service.getAll();
	}

	@GetMapping("/id/{id}")
	public Optional<Aposta> getApostaById(@PathVariable Long id){
		return service.getApostaById(id);
	}

	@GetMapping("/{email}")
	public List<Aposta> getApostaByEmail(@PathVariable String email){
		return service.getApostaByEmail(email);
	}

	@PostMapping("/{email}")
	public Aposta saveAposta(@PathVariable String email) {
		return service.createAposta(email);
	}

	@DeleteMapping("/{id}")
	public void deleteAposta(@PathVariable Long id) {
		System.out.println(id);
		service.deleteAposta(id);
	}
}
