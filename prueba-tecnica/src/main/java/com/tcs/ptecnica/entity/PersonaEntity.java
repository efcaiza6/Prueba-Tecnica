package com.tcs.ptecnica.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonaEntity {
    @Id
    private String identificacion;
    private String nombre;
    private String genero;
    private int edad;
    private String direccion;
    private String telefono;
}
