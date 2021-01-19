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

import com.orangetalents.orangetalents.Models.Aposta;
import com.orangetalents.orangetalents.Models.Numbers;
import com.orangetalents.orangetalents.Repository.ApostaRepository;
import com.orangetalents.orangetalents.Services.ApostaService;
@Service
public class ApostaServImpl implements ApostaService {
	@Autowired
	private ApostaRepository apostaRep;
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Optional<Aposta> getApostaById(Long id) {
		return apostaRep.findById(id);
	}

	@Override
	public List<Aposta> getAll() {
		return apostaRep.getAllApostas();
	}

	@Override
	public List<Aposta> getApostaByEmail(String email) {
		return manager.createQuery("select distinct v " + 
				"from Aposta v join fetch v.betNumbers p where p.aposta.email = '"+ email+"'", Aposta.class).getResultList();
	}

	@Override
	public Aposta createAposta(String email) {
		Aposta aposta = new Aposta();
		aposta.setEmail(email);
		Set<Numbers> gerados = Generator.NumGenerator(aposta); 
		List<Aposta> apostasByEmail = getApostaByEmail(email);
		for(Aposta n : apostasByEmail) {
			System.out.println(gerados.stream().map( v -> v.getGeneratedNumber()).collect(Collectors.toSet()));
			System.out.println(n.getBetNumbers());
			while(n.getBetNumbers().equals(n.getBetNumbers().equals(gerados.stream().map( v -> v.getGeneratedNumber()).collect(Collectors.toSet())))){
				System.out.println("numeros iguais gerados!");
				gerados = Generator.NumGenerator(aposta);
			}
		}
		aposta.setBetNumbers(gerados);
		return apostaRep.save(aposta);
	}

	@Override
	public void deleteAposta(Long id) {
		apostaRep.deleteById(id);
	}
	
}
