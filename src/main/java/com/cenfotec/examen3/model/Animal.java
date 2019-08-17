package com.cenfotec.examen3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cenfotec.examen3.model.Estado.EstadoBuilder;
import com.cenfotec.examen3.model.Pais.PaisBuilder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "animal")
@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor
public class Animal {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@Column(name = "nombre_cientifico")
	private String nombreCientifico;
	
	@Column(name = "peligro")
	private String PeligroExtincion;
	
	@Column(name = "poblacion")
	private int poblacion;
	
	@OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonIgnoreProperties({"animal"})
	private List<NombresPopulares> nombresPopulares = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "pais_id")
	@JsonIgnoreProperties({"nombre", "continente", "superficieTerrestre", "superficieMaritima", "estados", "regiones"})
	private Pais pais;	
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estado )) return false;
        return id != null && id.equals(((Estado) o).getId());
    }
    @Override
    public int hashCode() {
        return 31;
    }
}
