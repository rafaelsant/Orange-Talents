package com.orangetalents.orangetalents.Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.orangetalents.orangetalents.Generator;
import com.orangetalents.orangetalents.DTO.BetDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orangetalents.orangetalents.Models.Bet;
import com.orangetalents.orangetalents.Models.Numbers;
import com.orangetalents.orangetalents.Models.Params;
import com.orangetalents.orangetalents.Repository.BetRepository;
import com.orangetalents.orangetalents.Repository.ParamsRepository;
import com.orangetalents.orangetalents.Services.BetService;
@Service
public class BetServImpl implements BetService {

	private BetRepository apostaRep;
	private ParamsRepository params;

	public BetServImpl(BetRepository apostaRep, ParamsRepository params) {
		this.apostaRep = apostaRep;
		this.params = params;
	}

	@Override
	public Optional<Bet> getBetById(Long id) {
		Optional<Bet> bet = apostaRep.findById(id); 
		return bet;
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
		List<Bet> bets= getBetByEmail(email);
		bets.forEach(bet->ans.add(new BetDTO(bet.getEmail(),bet.getBetNumbers())));
		return ans;
	}

	@Override
	public List<Bet> getBetByEmail(String email) {
		List<Bet> bets= apostaRep.getBetsByEmail(email);
		return bets;
	}
	@Override
	public BetDTO createBet(String email) {
		Bet aposta = new Bet();
		aposta.setEmail(email);
		Optional<Params> parameters = params.getParams(); 
		Set<Numbers> gerados = Generator.NumGenerator(aposta,parameters.get().getBET_MAX_QUANTITY(),parameters.get().getBET_MAX_NUMBER()); 
		List<Bet> apostasByEmail = getBetByEmail(email);
		for(Bet n : apostasByEmail) {
			while(n.getBetNumbers()
					.equals(gerados.stream()
							.map( v -> v.getGeneratedNumber())
							.collect(Collectors.toSet()))){
				System.out.println("numeros iguais gerados!");
				gerados = Generator.NumGenerator(aposta,parameters.get().getBET_MAX_QUANTITY(),parameters.get().getBET_MAX_NUMBER());
			}
		}
		aposta.setBetNumbers(gerados);
		apostaRep.save(aposta);
		return new BetDTO(aposta.getEmail(),aposta.getBetNumbers());
	}

	@Override
	public void deleteBet(Long id) {
		apostaRep.deleteById(id);
	}

	@Override
	public BetDTO updateBet(Long id, String email) {
		Bet bet = getBetById(id).get();
		bet.setEmail(email);
		apostaRep.save(bet);
		BetDTO ans = new BetDTO(bet.getEmail(),bet.getBetNumbers());
		return ans;
	}
	
}
