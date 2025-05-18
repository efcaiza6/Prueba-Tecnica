package com.tcs.ptecnica.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "cliente")
public class ClienteEntity extends PersonaEntity{
    @Column(name = "id_cliente",unique = true, nullable = false)
    private String clienteId;
    @Column(name = "contrasena")
    private String contrasena;
    @Column(name = "estado")
    private String estado;
}
