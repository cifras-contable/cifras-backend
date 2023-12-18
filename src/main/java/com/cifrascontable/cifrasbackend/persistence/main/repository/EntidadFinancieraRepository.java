package com.cifrascontable.cifrasbackend.persistence.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cifrascontable.cifrasbackend.persistence.EntidadFinanciera;

@Repository
public interface EntidadFinancieraRepository extends JpaRepository<EntidadFinanciera, Long> {

}