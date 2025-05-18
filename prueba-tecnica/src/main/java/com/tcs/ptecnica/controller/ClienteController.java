package com.tcs.ptecnica.controller;

import com.tcs.ptecnica.entity.ClienteEntity;
import com.tcs.ptecnica.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ClienteEntity cliente) {
        try {
            ClienteEntity cliente1 = clienteService.save(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(cliente1);
        }catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("fecha", LocalDateTime.now());
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping
    public ResponseEntity<List<ClienteEntity>> getAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteEntity> getById(@PathVariable String id) {
        return clienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteEntity> update(@PathVariable String id, @RequestBody ClienteEntity cliente) {
        return ResponseEntity.ok(clienteService.update(id, cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
