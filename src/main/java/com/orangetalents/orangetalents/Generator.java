package com.orangetalents.orangetalents;

import java.util.Random;
import java.util.Set;
import java.util.LinkedHashSet;

import com.orangetalents.orangetalents.Models.Bet;
import com.orangetalents.orangetalents.Models.Numbers;


public class Generator {
	public static Set<Numbers> NumGenerator(Bet aposta,Integer qtd,Integer range) {
		Set<Integer> noRep = new LinkedHashSet<Integer>();
        Set<Numbers> numeros = new LinkedHashSet<Numbers>();
        Random rand = new Random();
        Integer generated = 0;
        while (noRep.size() < qtd) {
        	generated = rand.nextInt(range) + 1;
        	noRep.add(generated);
        }
        for(Integer n : noRep) {
        	Numbers numero = new Numbers(n,aposta);
        	numeros.add(numero);}
        return numeros;
    }
}
