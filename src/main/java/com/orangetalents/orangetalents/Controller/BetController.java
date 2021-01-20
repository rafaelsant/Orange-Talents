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

import com.orangetalents.orangetalents.Models.Bet;
import com.orangetalents.orangetalents.Services.BetService;

@RestController
@RequestMapping("api/aposta")
public class BetController {
	@Autowired
	private BetService service;
	
	@GetMapping
	public List<Bet> getBets(){
		return service.getAll();
	}
	@GetMapping("/id/{id}")
	public Optional<Bet> getBetById(@PathVariable Long id){
		return service.getBetById(id);
	}
	@GetMapping("/{email}")
	public ResponseEntity<List<Bet>> getBetByEmail(@Valid @PathVariable String email){
		Optional<List<Bet>> bet = Optional.of(service.getBetByEmail(email));
		if(!service.getBetByEmail(email).isEmpty()) {
			return ResponseEntity.ok(bet.get());
		}
			return ResponseEntity.notFound().build();
	}
	@PostMapping("/{email}")
	@ResponseStatus(HttpStatus.CREATED)
	public Bet saveBet(@PathVariable @Valid  String email) {
		return service.createBet(email);
	}
	@DeleteMapping("/{id}")
	public void deleteBet(@PathVariable Long id) {
		System.out.println(id);
		service.deleteBet(id);
	}
}
