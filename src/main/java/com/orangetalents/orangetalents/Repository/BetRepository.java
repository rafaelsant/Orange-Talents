package com.orangetalents.orangetalents.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.orangetalents.orangetalents.Models.Bet;

public interface BetRepository  extends JpaRepository<Bet, Long>{
	@Query("select distinct v from Bet v join fetch v.betNumbers")
	List<Bet> getAllBets();
}
