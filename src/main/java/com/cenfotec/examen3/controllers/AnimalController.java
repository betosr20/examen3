package com.cenfotec.examen3.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.examen3.model.Animal;
import com.cenfotec.examen3.model.NombresPopulares;
import com.cenfotec.examen3.model.Pais;
import com.cenfotec.examen3.repository.AnimalRepository;
import com.cenfotec.examen3.repository.NombresPopularesRepository;
import com.cenfotec.examen3.repository.PaisRepository;

@RestController
@RequestMapping("/animales")
public class AnimalController {
	
	
	
	private PaisRepository paisRepo;
	private NombresPopularesRepository nombresRepo;
	private AnimalRepository animalRepo;
	
	AnimalController(PaisRepository paisRepo, AnimalRepository animalRepo, NombresPopularesRepository nombresRepo){       
		  this.paisRepo = paisRepo;
		  this.nombresRepo = nombresRepo;
		  this.animalRepo = animalRepo;
	  }
	
	
	
	@GetMapping
	public List<Animal> findAll(){
		return animalRepo.findAll();
	}
	
	@PostMapping
	public Animal create(@RequestBody Animal animal){
		
		List<NombresPopulares> nombres = new ArrayList<>();
		Animal nuevoAnimal = animalRepo.save(animal);
		if(animal.getNombresPopulares().size() != 0 || animal.getNombresPopulares() != null) {
			for (NombresPopulares nombre : animal.getNombresPopulares()){ 
				nombre.setAnimal(nuevoAnimal);
				nombres.add(nombresRepo.save(nombre));
			}			
			//nuevoPais.setEstados(estados);
		}
		return nuevoAnimal;
	}
	
    @GetMapping("/animales_pais/{id}")
    public ResponseEntity<List<Animal>> findByPaisId(@PathVariable Long id){   
    	
    	List<Animal> animales= new ArrayList<Animal>();
    	Pais paisBuscar = paisRepo.findById(id).get();
        if(paisBuscar == null) {
        	return ResponseEntity.notFound().build();
        }
    	 animales = animalRepo.findByPais(paisBuscar);        
         if(animales != null) {
        	 return new ResponseEntity<List<Animal>>(animales, HttpStatus.OK);
         }else {
        	 return ResponseEntity.notFound().build();
         }
    }
    
    @GetMapping("/animales_nombre/{nombre}")
    public ResponseEntity<List<Animal>> findByName(@PathVariable String nombre){   
    	
    	List<Animal> animales= new ArrayList<Animal>();
    	animales = animalRepo.findByNameAproximation(nombre);      
         if(animales != null) {
        	 return new ResponseEntity<List<Animal>>(animales, HttpStatus.OK);
         }else {
        	 return ResponseEntity.notFound().build();
         }
    }

}
