package com.tcs.ptecnica.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "cliente")
public class ClienteEntity extends PersonaEntity{
    @Column(name = "id_cliente",unique = true, nullable = false)
    private String clienteId;
    @NotBlank(message = "La contraseña es obligatoria")
    @Column(name = "contrasena", nullable = false)
    private String contrasena;
    @NotBlank(message = "El estado es obligatorio")
    @Column(name = "estado", nullable = false)
    private String estado;
}
