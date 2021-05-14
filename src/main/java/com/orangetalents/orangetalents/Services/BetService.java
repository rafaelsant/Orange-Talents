package com.orangetalents.orangetalents.Services;

import java.util.List;

import java.util.Optional;

import com.orangetalents.orangetalents.Models.Bet;

import DTO.BetDTO;

public interface BetService {
	public Optional<BetDTO> getBetById(Long id);
	public List<BetDTO> getAll();
	public List<BetDTO> getBetByEmailDTO(String email);
	public List<Bet> getBetByEmail(String email);
	public BetDTO createBet(String email);
	public void deleteBet(Long id);
}
