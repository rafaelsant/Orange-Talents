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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.orangetalents.orangetalents.Models.Bet;
import com.orangetalents.orangetalents.Services.BetService;

import DTO.BetDTO;
import DTO.EmailDTO;

@RestController
@RequestMapping("api/aposta")
public class BetController {
	@Autowired
	private BetService service;
	
	@GetMapping
	public List<BetDTO> getBets(){
		return service.getAll();
	}
	@GetMapping("/id/{id}")
	public Optional<BetDTO> getBetById(@PathVariable Long id){
		return service.getBetById(id);
	}
	@GetMapping("/email")
	public ResponseEntity<List<BetDTO>> getBetByEmail(@RequestBody EmailDTO email){
		Optional<List<BetDTO>> bet = Optional.of(service.getBetByEmailDTO(email.getEmail()));
		if(!service.getBetByEmailDTO(email.getEmail()).isEmpty()) {
			return ResponseEntity.ok(bet.get());
		}
			return ResponseEntity.notFound().build();
	}
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public BetDTO saveBet(@RequestBody EmailDTO email) {
		return service.createBet(email.getEmail());
	}
	@DeleteMapping("/{id}")
	public void deleteBet(@PathVariable Long id) {
		System.out.println(id);
		service.deleteBet(id);
	}
}
