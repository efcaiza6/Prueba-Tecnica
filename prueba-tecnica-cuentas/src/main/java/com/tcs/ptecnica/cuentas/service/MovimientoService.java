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

    /**
     * Registra un nuevo movimiento en una cuenta existente.
     *
     * @param movimiento información del movimiento a registrar
     * @return el movimiento registrado
     * @throws RuntimeException si el movimiento es un retiro y no se tiene suficiente saldo
     */
    public MovimientoEntity save(MovimientoRQ movimiento) {
        CuentaEntity cuenta = cuentaRepository.findByNumeroCuenta(movimiento.getNumeroCuenta())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        MovimientoEntity movimientoEntity = new MovimientoEntity();
        // Set fecha automática al guardar
        double saldo = cuenta.getSaldoInicial();
        if (movimiento.getValor() > 0) {
            //Depósito
            cuenta.setSaldoInicial(saldo + movimiento.getValor());
            movimientoEntity.setFecha(LocalDateTime.now());
            movimientoEntity.setTipoMovimiento("Deposito");
            movimientoEntity.setValor(movimiento.getValor());
            movimientoEntity.setSaldo(cuenta.getSaldoInicial());
            movimientoEntity.setCuenta(cuenta);
            cuentaRepository.save(cuenta);
        }else {
            //Retiro
            cuenta.setSaldoInicial(saldo + movimiento.getValor());
            movimientoEntity.setFecha(LocalDateTime.now());
            movimientoEntity.setTipoMovimiento("Retiro");
            movimientoEntity.setValor(movimiento.getValor());
            movimientoEntity.setSaldo(cuenta.getSaldoInicial());
            movimientoEntity.setCuenta(cuenta);
            //Validación de saldo
            if (movimientoEntity.getSaldo() < 0) {
                throw new RuntimeException("No tiene suficiente saldo para realizar el retiro");
            }
            cuentaRepository.save(cuenta);
        }
        return movimientoRepository.save(movimientoEntity);
    }

    /**
     * Lista todos los movimientos registrados.
     */
    public List<MovimientoEntity> findAll() {
        return movimientoRepository.findAll();
    }

    /**
     * Busca un movimiento por ID.
     */
    public Optional<MovimientoEntity> findById(Integer id) {
        return movimientoRepository.findById(id);
    }

    /**
     * Actualiza un movimiento registrado.
     * @param id Identificador del movimiento que se va a actualizar.
     * @param updated Movimiento con los nuevos valores.
     * @return El movimiento actualizado.
     * @throws RuntimeException si el movimiento o la cuenta asociada no existen,
     *         o si el retiro va a dejar el saldo en negativo.
     */
    public MovimientoEntity update(Integer id, MovimientoRQ updated) {
        CuentaEntity cuenta = cuentaRepository.findByNumeroCuenta(updated.getNumeroCuenta())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        return movimientoRepository.findById(id)
                .map(mov -> {
                    Double saldoAnterior = mov.getSaldo()-mov.getValor();
                    if (updated.getValor() > 0) {
                        //Actualizar saldo
                        mov.setSaldo(saldoAnterior + updated.getValor());
                        mov.setTipoMovimiento("Deposito");
                        //Asigar nuevo saldo
                        mov.setValor(updated.getValor());
                        cuenta.setSaldoInicial(mov.getSaldo());
                    }else {
                        //Actualizar saldo
                        mov.setSaldo(saldoAnterior + updated.getValor());
                        mov.setTipoMovimiento("Retiro");
                        //Asigar nuevo saldo
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

    /**
     * Elimina un movimiento si existe, de lo contrario lanza excepción.
     */
    public void delete(Integer id) {
        if (!movimientoRepository.existsById(id)) {
            throw new RuntimeException("Movimiento no encontrado: " + id);
        }
        movimientoRepository.deleteById(id);
    }
}
