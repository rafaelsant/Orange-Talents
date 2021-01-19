package com.orangetalents.orangetalents.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orangetalents.orangetalents.Models.Aposta;
import com.orangetalents.orangetalents.Models.Numbers;

public interface NumbersRepository extends JpaRepository<Numbers, Long>{
	List<Numbers> findByAposta(Aposta aposta);
}
