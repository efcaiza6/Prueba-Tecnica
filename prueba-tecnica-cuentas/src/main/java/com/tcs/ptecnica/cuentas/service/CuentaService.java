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

    /**
     * Guarda una nueva cuenta en la base de datos.
     */
    public CuentaEntity save(CuentaEntity cuenta) {
        return cuentaRepository.save(cuenta);
    }

    /**
     * Retorna la lista de todas las cuentas.
     */
    public List<CuentaEntity> findAll() {
        return cuentaRepository.findAll();
    }

    /**
     * Busca una cuenta por su ID.
     * @param numeroCuenta ID numérico de la cuenta
     * @return Optional con la cuenta encontrada o vacío
     */
    public Optional<CuentaEntity> findById(Integer numeroCuenta) {
        return cuentaRepository.findById(numeroCuenta);
    }

    /**
     * Actualiza los datos de una cuenta existente.
     * Lanza excepción si la cuenta no existe.
     */
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

    /**
     * Elimina una cuenta si existe; lanza excepción si no.
     */
    public void delete(Integer numeroCuenta) {
        if (!cuentaRepository.existsById(numeroCuenta)) {
            throw new RuntimeException("Cuenta no encontrada: " + numeroCuenta);
        }
        cuentaRepository.deleteById(numeroCuenta);
    }
}
