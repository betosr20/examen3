package com.cenfotec.examen3.controllers;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.examen3.model.Estado;
import com.cenfotec.examen3.model.Pais;
import com.cenfotec.examen3.repository.EstadoRepository;
import com.cenfotec.examen3.repository.NombresPopularesRepository;
import com.cenfotec.examen3.repository.PaisRepository;

@RestController
@RequestMapping("/nombresPopulares")
public class NombresPopularesController {

	private PaisRepository paisRepo;
	private EstadoRepository estadoRepo;
	private NombresPopularesRepository nombresRepo;
	
	NombresPopularesController(PaisRepository paisRepo, EstadoRepository estadoRepo, NombresPopularesRepository nombresRepo){       
		  this.paisRepo = paisRepo;
		  this.estadoRepo = estadoRepo;
		  this.nombresRepo = nombresRepo;
	  }

	
	
	
}
