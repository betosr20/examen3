package com.cenfotec.examen3.controllers;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.PutMapping; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.examen3.model.Estado;
import com.cenfotec.examen3.model.Pais;
import com.cenfotec.examen3.repository.EstadoRepository;
import com.cenfotec.examen3.repository.PaisRepository;

@RestController
@RequestMapping("/estado")
public class EstadoController {
	private PaisRepository paisRepo;
	private EstadoRepository estadoRepo;
	
	
	
	EstadoController(PaisRepository paisRepo, EstadoRepository estadoRepo){       
		  this.paisRepo = paisRepo;
		  this.estadoRepo = estadoRepo;
	  }
	
	@GetMapping
	public List findAll(){
		return estadoRepo.findAll();
	}
	
	@PostMapping
	public Estado create(@RequestBody Estado estado){
		if(estado.getPais() != null) {
			estado.setPais(paisRepo.findByNombre(estado.getPais().getNombre()));
		}
		return estadoRepo.save(estado);
	}
	
    @PutMapping(value="/{id}") 
    public ResponseEntity<Estado> updateEstado(@PathVariable("id") long id,@RequestBody Estado estado) throws URISyntaxException {

		if(estado.getPais() != null) {
			estado.setPais(paisRepo.findByNombre(estado.getPais().getNombre()));
		}
		Estado result = estadoRepo.save(estado);
		
        return ResponseEntity.ok()
            .body(result);
    }
    
    @GetMapping("/estados_pais/{id}")
    public ResponseEntity<List<Estado>> findByPaisId(@PathVariable Long id){   
    	
    	List<Estado> estados= new ArrayList();
    	Pais paisBuscar = paisRepo.findById(id).get();
        if(paisBuscar == null) {
        	return ResponseEntity.notFound().build();
        }
    	 estados = estadoRepo.findByPais(paisBuscar);        
         if(estados != null) {
        	 return new ResponseEntity<List<Estado>>(estados, HttpStatus.OK);
         }else {
        	 return ResponseEntity.notFound().build();
         }
    }
    
    @GetMapping("/estados_nombre/{nombre}")
    public ResponseEntity<List<Estado>> findByName(@PathVariable String nombre){   
    	
    	List<Estado> estados= new ArrayList();
    	estados = estadoRepo.findByFirstNameAproximation(nombre);      
         if(estados != null) {
        	 return new ResponseEntity<List<Estado>>(estados, HttpStatus.OK);
         }else {
        	 return ResponseEntity.notFound().build();
         }
    }
    
    
    
    
    
    
    
    
}
