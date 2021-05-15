package com.orangetalents.orangetalents.Implementation;

import java.util.ArrayList;
import java.util.LinkedHashSet;
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
import com.orangetalents.orangetalents.Models.Params;
import com.orangetalents.orangetalents.Repository.BetRepository;
import com.orangetalents.orangetalents.Repository.ParamsRepository;
import com.orangetalents.orangetalents.Services.BetService;

import DTO.BetDTO;
@Service
public class BetServImpl implements BetService {
	@Autowired
	private BetRepository apostaRep;
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private ParamsRepository params;
	
	@Override
	public Optional<BetDTO> getBetById(Long id) {
		Optional<Bet> bet = apostaRep.findById(id);
		Optional<BetDTO> ans = Optional.of(new BetDTO(bet.get().getEmail(),bet.get().getBetNumbers())); 
		return ans;
	}

	@Override
	public List<BetDTO> getAll() {
		List<BetDTO> ans = new ArrayList<BetDTO>();
		List<Bet> bets = apostaRep.getAllBets();
		bets.forEach(bet->ans.add(new BetDTO(bet.getEmail(),bet.getBetNumbers())));
		return ans;
	}

	@Override
	public List<BetDTO> getBetByEmailDTO(String email) {
		List<BetDTO> ans = new ArrayList<BetDTO>();
		List<Bet> bets= manager.createQuery("select distinct v " + 
				"from Bet v join fetch "+ 
				"v.betNumbers p where p.bet.email = '"
				+ email+"'", Bet.class).getResultList();
		bets.forEach(bet->ans.add(new BetDTO(bet.getEmail(),bet.getBetNumbers())));
		return ans;
	}

	@Override
	public List<Bet> getBetByEmail(String email) {
		List<Bet> bets= manager.createQuery("select distinct v " + 
				"from Bet v join fetch "+ 
				"v.betNumbers p where p.bet.email = '"
				+ email+"'", Bet.class).getResultList();
		return bets;
	}
	@Override
	public BetDTO createBet(String email) {
		Bet aposta = new Bet();
		aposta.setEmail(email);
		Optional<Params> param = params.findById(1);
		System.out.println(param);
		Set<Numbers> gerados = Generator.NumGenerator(aposta,param.get().getQuantity(),param.get().getRange()); 
		List<Bet> apostasByEmail = getBetByEmail(email);
		for(Bet n : apostasByEmail) {
			System.out.println(gerados.stream().map( v -> v.getGeneratedNumber()).collect(Collectors.toSet()));
			System.out.println(n.getBetNumbers());
			while(n.getBetNumbers()
					.equals(gerados.stream()
							.map( v -> v.getGeneratedNumber())
							.collect(Collectors.toSet()))){
				System.out.println("numeros iguais gerados!");
				gerados = Generator.NumGenerator(aposta,param.get().getQuantity(),param.get().getRange());
			}
		}
		aposta.setBetNumbers(gerados);
		BetDTO ans = new BetDTO(aposta.getEmail(),aposta.getBetNumbers());
		apostaRep.save(aposta);
		return ans;
	}

	@Override
	public void deleteBet(Long id) {
		apostaRep.deleteById(id);
	}
	
}
