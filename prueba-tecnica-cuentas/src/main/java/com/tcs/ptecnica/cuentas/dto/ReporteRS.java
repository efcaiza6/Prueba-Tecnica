package com.tcs.ptecnica.cuentas.dto;

import lombok.Data;

@Data
public class ReporteRS {
    private String fecha;
    private String clienteNombre;
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private String estadoCuenta;
    private String tipoMovimiento;
    private Double saldoFinal;
}
