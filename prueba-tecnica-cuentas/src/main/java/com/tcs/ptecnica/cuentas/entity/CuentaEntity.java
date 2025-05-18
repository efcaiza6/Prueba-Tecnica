package com.tcs.ptecnica.cuentas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "cuenta")
public class CuentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private Integer id;
    @NotNull(message = "El número de cuenta es obligatorio")
    @Column(name = "numero_cuenta", unique = true, nullable = false)
    private String numeroCuenta;
    @NotBlank(message = "El tipo de cuenta no puede estar en blanco")
    @Column(name = "tipo_cuenta", nullable = false)
    private String tipoCuenta;
    @NotNull(message = "El saldo inicial es obligatorio")
    @Column(name = "saldo_inicial", nullable = false)
    private Double saldoInicial;
    @NotBlank(message = "El estado es obligatorio")
    @Column(name = "estado", nullable = false)
    private String estado;
    @NotBlank(message = "El ID del cliente es obligatorio")
    @Column(name = "cliente_id", nullable = false)
    private String clienteId;
}
