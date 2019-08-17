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

import com.cenfotec.examen3.model.Estado;
import com.cenfotec.examen3.model.Pais;
import com.cenfotec.examen3.model.Region;
import com.cenfotec.examen3.repository.EstadoRepository;
import com.cenfotec.examen3.repository.PaisRepository;
import com.cenfotec.examen3.repository.RegionRepository;

@RestController
@RequestMapping("/region")
public class RegionController {

	private PaisRepository paisRepo;
	private EstadoRepository estadoRepo;
	private RegionRepository regionRepo;

	RegionController(PaisRepository paisRepo, EstadoRepository estadoRepo, RegionRepository regionRepo){       
		  this.paisRepo = paisRepo;
		  this.estadoRepo = estadoRepo;
		  this.regionRepo = regionRepo;
	  }
	
	@GetMapping
	public List findAll(){
		return regionRepo.findAll();
	}
	
	@PostMapping
	public Region create(@RequestBody Region region){
		Region regionModificar = region;
		System.out.println("ENTRANDO");
		System.out.println(region);
		if(region.getPais() != null) {
			System.out.println("PAIS BUSCAR");
			System.out.println(region.getPais().getId());
			Pais nuevoPais = paisRepo.findById(region.getPais().getId()).get();
			regionModificar.setPais(nuevoPais);

		}

		List<Estado> estados = new ArrayList<>();
		estados = regionModificar.getEstados();	
		Region regionSinEstado = regionModificar;
		regionSinEstado.setEstados(null);
		Region nuevaRegion = regionRepo.save(regionSinEstado);
		nuevaRegion.setEstados(estados);
		Estado estadoObtenido;

		if(nuevaRegion.getEstados() != null) {
			for (Estado estado : nuevaRegion.getEstados()){ 
				estadoObtenido = estadoRepo.findById(estado.getId()).get();
				estadoObtenido.setRegion(nuevaRegion);

				estadoRepo.save(estadoObtenido);
			}			
		}	
		

		return nuevaRegion;
	}
    @GetMapping("/regiones_pais/{id}")
    public ResponseEntity<List<Region>> findByPaisId(@PathVariable Long id){   
    	
    	List<Region> regiones= new ArrayList();
    	Pais paisBuscar = paisRepo.findById(id).get();
        if(paisBuscar == null) {
        	return ResponseEntity.notFound().build();
        }
        regiones = regionRepo.findByPais(paisBuscar);        
         if(regiones != null) {
        	 return new ResponseEntity<List<Region>>(regiones, HttpStatus.OK);
         }else {
        	 return ResponseEntity.notFound().build();
         }
    }
    
    @GetMapping("/regiones_nombre/{nombre}")
    public ResponseEntity<List<Region>> findByName(@PathVariable String nombre){   
    	
    	List<Region> regiones= new ArrayList();
    	regiones = regionRepo.findByFirstNameAproximation(nombre);      
         if(regiones != null) {
        	 return new ResponseEntity<List<Region>>(regiones, HttpStatus.OK);
         }else {
        	 return ResponseEntity.notFound().build();
         }
    }
}
