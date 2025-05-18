package com.tcs.ptecnica.cuentas.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "movimiento")
public class MovimientoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Integer id;
    @Column(name = "fecha")
    private LocalDateTime fecha;
    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;
    @Column(name = "valor")
    private Double valor;
    @Column(name = "saldo")
    private Double saldo;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private CuentaEntity cuenta;
}
