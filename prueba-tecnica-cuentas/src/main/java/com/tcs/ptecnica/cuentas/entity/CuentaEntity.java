package com.tcs.ptecnica.cuentas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cuenta")
public class CuentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private Integer id;
    @Column(name = "numero_cuenta", unique = true)
    private String numeroCuenta;
    @Column(name = "tipo_cuenta")
    private String tipoCuenta;
    @Column(name = "saldo_inicial")
    private Double saldoInicial;
    @Column(name = "estado")
    private String estado;
    @Column(name = "cliente_id")
    private String clienteId;
}
