package com.tcs.ptecnica.cuentas.repository;

import com.tcs.ptecnica.cuentas.entity.CuentaEntity;
import com.tcs.ptecnica.cuentas.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<MovimientoEntity, Integer> {
    List<MovimientoEntity> findByCuentaAndFechaBetween(CuentaEntity cuenta, LocalDateTime inicio, LocalDateTime fin);

}
