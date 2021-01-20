package com.orangetalents.orangetalents.Controller;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.orangetalents.orangetalents.Models.Aposta;
import com.orangetalents.orangetalents.Services.ApostaService;

@RestController
@RequestMapping("api/aposta")
public class ApostaController {
	@Autowired
	private ApostaService service;
	
	@GetMapping
	public List<Aposta> getApostas(){
		return service.getAll();
	}
	@GetMapping("/id/{id}")
	public Optional<Aposta> getApostaById(@PathVariable Long id){
		return service.getApostaById(id);
	}
	@GetMapping("/{email}")
	public ResponseEntity<List<Aposta>> getApostaByEmail(@Valid @PathVariable String email){
		Optional<List<Aposta>> aposta = Optional.of(service.getApostaByEmail(email));
		if(!service.getApostaByEmail(email).isEmpty()) {
			return ResponseEntity.ok(aposta.get());
		}
			return ResponseEntity.notFound().build();
	}
	@PostMapping("/{email}")
	@ResponseStatus(HttpStatus.CREATED)
	public Aposta saveAposta(@PathVariable @Valid  String email) {
		return service.createAposta(email);
	}
	@DeleteMapping("/{id}")
	public void deleteAposta(@PathVariable Long id) {
		System.out.println(id);
		service.deleteAposta(id);
	}
}
