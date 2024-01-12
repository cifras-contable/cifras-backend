package com.cifrascontable.cifrasbackend.persistence.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="entidades_financieras")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntidadFinanciera {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "nombre", length = 50)
	private String nombre;
	
	@Column(name = "tipo_de_entidad_financiera", length = 10)
	private String tipo;
	
	@Column(name = "numero_de_cuenta", length = 15)
	private String numeroCuenta;
	
	@Column(name = "cuit", length = 20)
	private String cuit;
	
	@Column(name = "cbu", length = 30)
	private String cbu;
	
	@Column(name = "alias", length = 50)
	private String alias;
	
	@Column(name = "id_nt")
	private Integer idTransaccion;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empresa", nullable = false)
	private Empresa empresa;

}
