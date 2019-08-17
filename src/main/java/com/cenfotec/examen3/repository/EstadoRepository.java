package com.cenfotec.examen3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cenfotec.examen3.model.Estado;
import com.cenfotec.examen3.model.Pais;

public interface EstadoRepository extends JpaRepository<Estado, Long>{

	
	List<Estado> findByPais(Pais pais);
	
	@Query(nativeQuery = true, value = "SELECT * FROM estado "
            + "WHERE nombre LIKE %:name%")
	public List<Estado> findByFirstNameAproximation(String name);

}
