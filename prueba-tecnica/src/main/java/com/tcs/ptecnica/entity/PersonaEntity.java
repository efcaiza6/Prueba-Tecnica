package com.tcs.ptecnica.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonaEntity {
    @Id
    private String identificacion;
    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "genero")
    private String genero;
    @Column(name = "edad", nullable = false)
    @Min(value = 18, message = "La edad no puede ser menor a 18")
    private int edad;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
}
