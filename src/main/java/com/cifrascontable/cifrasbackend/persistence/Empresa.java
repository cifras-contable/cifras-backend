package com.cifrascontable.cifrasbackend.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="empresa")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
	
	//TODO: Mapear id_grupo -> Grupo de empresas

	@Builder
	public Empresa(String cuit, String razonSocial, String nombreFantasia) {
		this.cuit = cuit;
		this.razonSocial = razonSocial;
		this.nombreFantasia = nombreFantasia;
	}
	

	
	
	
	
	
	
}
