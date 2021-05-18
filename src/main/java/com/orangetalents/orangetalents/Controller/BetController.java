package com.orangetalents.orangetalents.Controller;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.orangetalents.orangetalents.DTO.BetDTO;
import com.orangetalents.orangetalents.DTO.EmailDTO;
import com.orangetalents.orangetalents.Services.BetService;

@RestController
@RequestMapping("api/aposta")
public class BetController {
	@Autowired
	private BetService service;
	
	@GetMapping
	public List<BetDTO> getBets(){
		return service.getAll();
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<List<BetDTO>> getBetByEmail(@PathVariable String email){
		Optional<List<BetDTO>> bet = Optional.of(service.getBetByEmailDTO(email));
		if(!service.getBetByEmailDTO(email).isEmpty()) {
			return ResponseEntity.ok(bet.get());
		}
			return ResponseEntity.notFound().build();
	}
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public BetDTO saveBet(@RequestBody EmailDTO email) {
		return service.createBet(email.getEmail());
	}
	@PutMapping("/{id}")
	public ResponseEntity<BetDTO> updateBet(@PathVariable Long id, @RequestBody EmailDTO email){
		BetDTO ans = service.updateBet(id, email.getEmail());
		return ResponseEntity.ok(ans);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBet(@PathVariable Long id) {
		service.deleteBet(id);
		return ResponseEntity.noContent().build();
	}
}
