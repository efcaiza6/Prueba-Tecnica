package com.tcs.ptecnica.cuentas.controller;

import com.tcs.ptecnica.cuentas.entity.CuentaEntity;
import com.tcs.ptecnica.cuentas.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class CuentaController {
    private final CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<CuentaEntity> create(@RequestBody CuentaEntity cuenta) {
        return ResponseEntity.ok(cuentaService.save(cuenta));
    }

    @GetMapping
    public ResponseEntity<List<CuentaEntity>> getAll() {
        return ResponseEntity.ok(cuentaService.findAll());
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<CuentaEntity> getById(@PathVariable Integer numeroCuenta) {
        return cuentaService.findById(numeroCuenta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{numeroCuenta}")
    public ResponseEntity<CuentaEntity> update(@PathVariable Integer numeroCuenta, @RequestBody CuentaEntity cuenta) {
        return ResponseEntity.ok(cuentaService.update(numeroCuenta, cuenta));
    }

    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<Void> delete(@PathVariable Integer numeroCuenta) {
        cuentaService.delete(numeroCuenta);
        return ResponseEntity.noContent().build();
    }
}
