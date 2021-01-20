package com.orangetalents.orangetalents;

import java.util.Random;
import java.util.Set;

import com.orangetalents.orangetalents.Models.Bet;
import com.orangetalents.orangetalents.Models.Numbers;

import java.util.LinkedHashSet;

public class Generator {
	public static Set<Numbers> NumGenerator(Bet aposta) {
		Set<Integer> noRep = new LinkedHashSet<Integer>();
        Set<Numbers> numeros = new LinkedHashSet<Numbers>();
        Random rand = new Random();
        Integer generated = 0;
        while (noRep.size() < 6) {
        	generated = rand.nextInt(59) + 1;
        	noRep.add(generated);
        }
        for(Integer n : noRep) {
        	Numbers numero = new Numbers(n,aposta);
        	numeros.add(numero);}
        return numeros;
    }
}
