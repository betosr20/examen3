package com.cenfotec.examen3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cenfotec.examen3.model.Pais;
import com.cenfotec.examen3.model.Region;

public interface RegionRepository extends JpaRepository<Region, Long>{

	public List<Region> findByPais(Pais pais);
	
	@Query(nativeQuery = true, value = "SELECT * FROM region "
            + "WHERE nombre LIKE %:name%")
	public List<Region> findByFirstNameAproximation(String name);
}
