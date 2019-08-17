package com.cenfotec.examen3.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estado")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@Column(name = "nombre")
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "pais_id")
	@JsonIgnoreProperties({"estados", "regiones", "nombre", "continente", "superficieTerrestre", "superficieMaritima"})
	private Pais pais;
	
	@ManyToOne
	@JsonIgnoreProperties({"estados", "pais"})
	private Region region;	
	
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
