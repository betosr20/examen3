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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "region")
@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class Region {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@Column(name = "nombre")
	private String nombre;
	
	@OneToMany
	@JsonIgnoreProperties({"region", "pais"})
	private List<Estado> estados = new ArrayList<>();
	
    public void agregarEstado(Estado estado) {
        estados.add(estado);
        estado.setRegion(this);
    }
 
    public void eliminarEstado(Estado estado) {
    	estados.remove(estado);
    	estado.setRegion(null);
    }
    
	@ManyToOne
	@JoinColumn(name = "pais_id")
	@JsonIgnoreProperties({"estados", "regiones", "nombre", "continente", "superficieTerrestre", "superficieMaritima"})
	private Pais pais;
	
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
}
