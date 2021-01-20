package com.orangetalents.orangetalents.Services;

import java.util.List;

import java.util.Optional;

import com.orangetalents.orangetalents.Models.Bet;

public interface BetService {
	public Optional<Bet> getBetById(Long id);
	public List<Bet> getAll();
	public List<Bet> getBetByEmail(String email);
	public Bet createBet(String email);
	public void deleteBet(Long id);
}
