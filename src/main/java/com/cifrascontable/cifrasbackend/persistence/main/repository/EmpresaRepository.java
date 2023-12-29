package com.cifrascontable.cifrasbackend.persistence.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cifrascontable.cifrasbackend.persistence.main.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	
	public Optional<Empresa> findByCuit(String cuit);

}
