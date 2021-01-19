package com.orangetalents.orangetalents.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.orangetalents.orangetalents.Models.Aposta;

public interface ApostaRepository  extends JpaRepository<Aposta, Long>{
	@Query("select distinct v from Aposta v join fetch v.betNumbers")
	List<Aposta> getAllApostas();
}
