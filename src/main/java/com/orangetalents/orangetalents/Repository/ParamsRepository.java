package com.orangetalents.orangetalents.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.orangetalents.orangetalents.Models.Params;

public interface ParamsRepository extends JpaRepository<Params, Integer> {
	@Query(value = "SELECT * from params limit 1",nativeQuery = true)
	Optional<Params> getParams();
}
