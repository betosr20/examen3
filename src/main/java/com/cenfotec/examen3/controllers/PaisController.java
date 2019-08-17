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
import com.cenfotec.examen3.repository.PaisRepository;

@RestController
@RequestMapping("/pais")
public class PaisController {

	private PaisRepository paisRepo;
	private EstadoRepository estadoRepo;
	
	PaisController(PaisRepository paisRepo, EstadoRepository estadoRepo){       
		  this.paisRepo = paisRepo;
		  this.estadoRepo = estadoRepo;
	  }
	
	@GetMapping
	public List findAll(){
		return paisRepo.findAll();
	}
	
	@PostMapping
	public Pais create(@RequestBody Pais pais){
		
		List<Estado> estados = new ArrayList<>();
		Pais nuevoPais = paisRepo.save(pais);
		if(pais.getEstados().size() != 0 || pais.getEstados() != null) {
			for (Estado estado : pais.getEstados()){ 
				estado.setPais(nuevoPais);
				estados.add(estadoRepo.save(estado));
			}			
			nuevoPais.setEstados(estados);
		}
		return nuevoPais;
	}
	
    @PutMapping(value="/{id}") 
    public ResponseEntity<Pais> update(@PathVariable("id") long id,@RequestBody Pais pais) throws URISyntaxException {
    	List<Estado> estados = new ArrayList<>();
		if(pais.getEstados().size() != 0 || pais.getEstados() != null) {
			for (Estado estado : pais.getEstados()) 
			{ 
				estado.setPais(pais);
				estados.add(estadoRepo.save(estado));
			}			
		}
		pais.setEstados(estados);
		Pais result = paisRepo.save(pais);
		
        return ResponseEntity.ok()
            .body(result);
    }
}
