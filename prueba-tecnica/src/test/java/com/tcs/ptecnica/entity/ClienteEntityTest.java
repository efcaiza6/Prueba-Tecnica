package com.tcs.ptecnica.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteEntityTest {
    @Test
    void testClienteGetterSetter() {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setNombre("Marianela");
        cliente.setGenero("F");
        cliente.setEdad(30);
        cliente.setIdentificacion("1234567890");
        cliente.setDireccion("Quito");
        cliente.setTelefono("0999999999");
        cliente.setContrasena("clave123");
        cliente.setEstado("Activo");

        assertEquals("Marianela", cliente.getNombre());
        assertEquals("1234567890", cliente.getIdentificacion());
        assertEquals("Activo", cliente.getEstado());
    }
}
