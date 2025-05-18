package com.tcs.ptecnica.repository;

import com.tcs.ptecnica.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity, String> {
}
