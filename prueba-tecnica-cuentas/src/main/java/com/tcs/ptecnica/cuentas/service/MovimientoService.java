package com.tcs.ptecnica.cuentas.service;

import com.tcs.ptecnica.cuentas.dto.MovimientoRQ;
import com.tcs.ptecnica.cuentas.entity.CuentaEntity;
import com.tcs.ptecnica.cuentas.entity.MovimientoEntity;
import com.tcs.ptecnica.cuentas.repository.CuentaRepository;
import com.tcs.ptecnica.cuentas.repository.MovimientoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovimientoService {
    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public MovimientoEntity save(MovimientoRQ movimiento) {
        CuentaEntity cuenta = cuentaRepository.findByNumeroCuenta(movimiento.getNumeroCuenta())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        MovimientoEntity movimientoEntity = new MovimientoEntity();
        // Set fecha automática al guardar
        double saldo = cuenta.getSaldoInicial();
        if (movimiento.getValor() > 0) {
            cuenta.setSaldoInicial(saldo + movimiento.getValor());
            movimientoEntity.setFecha(LocalDateTime.now());
            movimientoEntity.setTipoMovimiento("Deposito");
            movimientoEntity.setValor(movimiento.getValor());
            movimientoEntity.setSaldo(cuenta.getSaldoInicial());
            movimientoEntity.setCuenta(cuenta);
            cuentaRepository.save(cuenta);
        }else {
            cuenta.setSaldoInicial(saldo + movimiento.getValor());
            movimientoEntity.setFecha(LocalDateTime.now());
            movimientoEntity.setTipoMovimiento("Retiro");
            movimientoEntity.setValor(movimiento.getValor());
            movimientoEntity.setSaldo(cuenta.getSaldoInicial());
            movimientoEntity.setCuenta(cuenta);
            if (movimientoEntity.getSaldo() < 0) {
                throw new RuntimeException("No tiene suficiente saldo para realizar el retiro");
            }
            cuentaRepository.save(cuenta);
        }
        return movimientoRepository.save(movimientoEntity);
    }

    public List<MovimientoEntity> findAll() {
        return movimientoRepository.findAll();
    }

    public Optional<MovimientoEntity> findById(Integer id) {
        return movimientoRepository.findById(id);
    }

    public MovimientoEntity update(Integer id, MovimientoRQ updated) {
        CuentaEntity cuenta = cuentaRepository.findByNumeroCuenta(updated.getNumeroCuenta())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        return movimientoRepository.findById(id)
                .map(mov -> {
                    Double saldoAnterior = mov.getSaldo()-mov.getValor();
                    if (updated.getValor() > 0) {
                        mov.setSaldo(saldoAnterior + updated.getValor());
                        mov.setTipoMovimiento("Deposito");
                        mov.setValor(updated.getValor());
                        cuenta.setSaldoInicial(mov.getSaldo());
                    }else {
                        mov.setSaldo(saldoAnterior + updated.getValor());
                        mov.setTipoMovimiento("Retiro");
                        mov.setValor(updated.getValor());
                        cuenta.setSaldoInicial(mov.getSaldo());
                        if (mov.getSaldo() < 0) {
                            throw new RuntimeException("No tiene suficiente saldo para realizar el retiro");
                        }
                    }
                    cuentaRepository.save(cuenta);
                    return movimientoRepository.save(mov);
                })
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado: " + id));
    }

    public void delete(Integer id) {
        if (!movimientoRepository.existsById(id)) {
            throw new RuntimeException("Movimiento no encontrado: " + id);
        }
        movimientoRepository.deleteById(id);
    }
}
