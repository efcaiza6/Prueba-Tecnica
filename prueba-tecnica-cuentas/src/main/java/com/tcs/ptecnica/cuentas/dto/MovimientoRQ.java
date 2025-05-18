package com.tcs.ptecnica.cuentas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MovimientoRQ {
    @NotNull(message = "El valor del movimiento es obligatorio")
    private Double valor;
    @NotBlank(message = "El número de cuenta es obligatorio")
    @Size(min = 5, max = 20, message = "El número de cuenta debe tener entre 5 y 10 caracteres")
    private String numeroCuenta;
}
