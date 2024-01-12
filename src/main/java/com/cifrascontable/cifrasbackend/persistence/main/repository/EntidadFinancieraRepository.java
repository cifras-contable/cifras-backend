package com.cifrascontable.cifrasbackend.persistence.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cifrascontable.cifrasbackend.persistence.main.model.EntidadFinanciera;

import java.util.Optional;

@Repository
public interface EntidadFinancieraRepository extends JpaRepository<EntidadFinanciera, Long> {
    public Optional<EntidadFinanciera> findByCuit(String cuit);
}