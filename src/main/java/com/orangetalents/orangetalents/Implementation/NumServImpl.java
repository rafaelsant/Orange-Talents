package com.orangetalents.orangetalents.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orangetalents.orangetalents.Models.Aposta;
import com.orangetalents.orangetalents.Models.Numbers;
import com.orangetalents.orangetalents.Repository.NumbersRepository;
import com.orangetalents.orangetalents.Services.NumbersService;
@Service
public class NumServImpl implements NumbersService {

	@Autowired
	NumbersRepository numbersRep;
	@Override
	public List<Numbers> getNumbersByAposta(Aposta aposta) {
		return numbersRep.findByAposta(aposta);
	}

	@Override
	public void delete(Long id) {
		numbersRep.deleteById(id);
		
	}
	
}
