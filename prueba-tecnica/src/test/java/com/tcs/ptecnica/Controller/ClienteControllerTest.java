package com.tcs.ptecnica.Controller;

import com.tcs.ptecnica.entity.ClienteEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ClienteControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    void testCrearCliente() {
        ClienteEntity cliente = new ClienteEntity();
        cliente.setNombre("Test");
        cliente.setGenero("M");
        cliente.setEdad(30);
        cliente.setIdentificacion("8888888888");
        cliente.setDireccion("Loja");
        cliente.setTelefono("0912345678");
        cliente.setContrasena("clave123");
        cliente.setEstado("Activo");
        cliente.setClienteId("cliente12333");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ClienteEntity> request = new HttpEntity<>(cliente, headers);

        ResponseEntity<ClienteEntity> response = restTemplate.postForEntity("/clientes", request, ClienteEntity.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getNombre()).isEqualTo("Test");
    }

    @Test
    void testObtenerTodosLosClientes() {
        // Act
        ResponseEntity<ClienteEntity[]> response = restTemplate.getForEntity("/clientes", ClienteEntity[].class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isGreaterThanOrEqualTo(0);
    }
}
