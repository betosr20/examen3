package com.cenfotec.examen3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pais")
@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class Pais {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "continente")
	private String continente;

	@Column(name = "superficie_terrestre")
	private double superficieTerrestre;
	
	@Column(name = "superficie_maritima")
	private double superficieMaritima;
	
	@OneToMany(mappedBy = "pais", cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonIgnoreProperties({"pais", "region"})
	private List<Estado> estados = new ArrayList<>();
	
	@OneToMany(mappedBy = "pais", cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonIgnoreProperties({"pais"})
	private List<Region> regiones = new ArrayList<>();
	
    public void agregarEstado(Estado estado) {
        estados.add(estado);
        estado.setPais(this);
    }
 
    public void eliminarEstado(Estado estado) {
    	estados.remove(estado);
    	estado.setPais(null);
    }
}
