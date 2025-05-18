package com.tcs.ptecnica.cuentas.repository;

import com.tcs.ptecnica.cuentas.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CuentaRepository extends JpaRepository<CuentaEntity, Integer> {
    Optional<CuentaEntity> findByNumeroCuenta(String numeroCuenta);
    List<CuentaEntity> findByClienteId(String clienteId);
}
