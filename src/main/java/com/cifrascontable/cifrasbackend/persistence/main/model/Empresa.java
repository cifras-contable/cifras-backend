package com.cifrascontable.cifrasbackend.persistence.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="empresa")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "cuit", length = 20)
	private String cuit;	

	@Column(name = "razon_social", length = 100)
	private String razonSocial;
	
	@Column(name = "nombre_de_fantasia", length = 100)
	private String nombreFantasia;

	@Column(name = "id_grupo")
	private Long idGrupo;

}
