package com.tcs.ptecnica.cuentas.service;

import com.tcs.ptecnica.cuentas.entity.CuentaEntity;
import com.tcs.ptecnica.cuentas.repository.CuentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CuentaService {
    private final CuentaRepository cuentaRepository;

    public CuentaEntity save(CuentaEntity cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public List<CuentaEntity> findAll() {
        return cuentaRepository.findAll();
    }

    public Optional<CuentaEntity> findById(Integer numeroCuenta) {
        return cuentaRepository.findById(numeroCuenta);
    }

    public CuentaEntity update(Integer numeroCuenta, CuentaEntity updatedCuenta) {
        return cuentaRepository.findById(numeroCuenta)
                .map(cuenta -> {
                    cuenta.setTipoCuenta(updatedCuenta.getTipoCuenta());
                    cuenta.setSaldoInicial(updatedCuenta.getSaldoInicial());
                    cuenta.setEstado(updatedCuenta.getEstado());
                    cuenta.setClienteId(updatedCuenta.getClienteId());
                    return cuentaRepository.save(cuenta);
                })
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada: " + numeroCuenta));
    }

    public void delete(Integer numeroCuenta) {
        if (!cuentaRepository.existsById(numeroCuenta)) {
            throw new RuntimeException("Cuenta no encontrada: " + numeroCuenta);
        }
        cuentaRepository.deleteById(numeroCuenta);
    }
}
