package com.tcs.ptecnica.repository;

import com.tcs.ptecnica.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {
    Optional<ClienteEntity> findByIdentificacion(String identificacion);
}
