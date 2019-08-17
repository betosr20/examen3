package com.cenfotec.examen3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cenfotec.examen3.model.Estado.EstadoBuilder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nombres")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NombresPopulares {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@Column(name = "nombres_populares")
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "animal_id")
	@JsonIgnoreProperties({})
	private Animal animal;
	
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
