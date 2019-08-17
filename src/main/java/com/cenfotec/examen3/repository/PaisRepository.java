package com.cenfotec.examen3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cenfotec.examen3.model.Pais;

public interface PaisRepository extends JpaRepository<Pais, Long>{

	public Pais findByNombre(String nombre);
}
