package com.orangetalents.orangetalents.Implementation;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.orangetalents.orangetalents.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orangetalents.orangetalents.Models.Bet;
import com.orangetalents.orangetalents.Models.Numbers;
import com.orangetalents.orangetalents.Repository.BetRepository;
import com.orangetalents.orangetalents.Services.BetService;
@Service
public class BetServImpl implements BetService {
	@Autowired
	private BetRepository apostaRep;
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Optional<Bet> getBetById(Long id) {
		return apostaRep.findById(id);
	}

	@Override
	public List<Bet> getAll() {
		return apostaRep.getAllBets();
	}

	@Override
	public List<Bet> getBetByEmail(String email) {
		return manager.createQuery("select distinct v " + 
				"from Bet v join fetch "+ 
				"v.betNumbers p where p.bet.email = '"
				+ email+"'", Bet.class).getResultList();
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Bet createBet(String email) {
		Bet aposta = new Bet();
		aposta.setEmail(email);
		Set<Numbers> gerados = Generator.NumGenerator(aposta); 
		List<Bet> apostasByEmail = getBetByEmail(email);
		for(Bet n : apostasByEmail) {
			System.out.println(gerados.stream().map( v -> v.getGeneratedNumber()).collect(Collectors.toSet()));
			System.out.println(n.getBetNumbers());
			while(n.getBetNumbers().equals(n.getBetNumbers()
					.equals(gerados.stream()
							.map( v -> v.getGeneratedNumber())
							.collect(Collectors.toSet())))){
				System.out.println("numeros iguais gerados!");
				gerados = Generator.NumGenerator(aposta);
			}
		}
		aposta.setBetNumbers(gerados);
		return apostaRep.save(aposta);
	}

	@Override
	public void deleteBet(Long id) {
		apostaRep.deleteById(id);
	}
	
}
