package com.orangetalents.orangetalents.Services;

import java.util.List;

import java.util.Optional;

import com.orangetalents.orangetalents.Models.Aposta;

public interface ApostaService {
	public Optional<Aposta> getApostaById(Long id);
	public List<Aposta> getAll();
	public List<Aposta> getApostaByEmail(String email);
	public Aposta createAposta(String email);
	public void deleteAposta(Long id);
}
