package com.cenfotec.examen3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cenfotec.examen3.model.Animal;
import com.cenfotec.examen3.model.Pais;

public interface AnimalRepository extends JpaRepository<Animal, Long>{
	List<Animal> findByPais(Pais pais);
	
	@Query(nativeQuery = true, value = "SELECT * FROM animal "
            + "WHERE nombre_cientifico LIKE %:name%")
	public List<Animal> findByNameAproximation(String name);
}
