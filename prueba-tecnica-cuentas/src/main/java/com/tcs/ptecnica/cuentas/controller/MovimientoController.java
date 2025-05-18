package com.tcs.ptecnica.cuentas.controller;

import com.tcs.ptecnica.cuentas.dto.MovimientoRQ;
import com.tcs.ptecnica.cuentas.entity.MovimientoEntity;
import com.tcs.ptecnica.cuentas.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoController {
    private final MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody MovimientoRQ movimiento) {
        try {
        MovimientoEntity movimientoEntity = movimientoService.save(movimiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(movimientoEntity);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("fecha", LocalDateTime.now());
            error.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping
    public ResponseEntity<List<MovimientoEntity>> getAll() {
        return ResponseEntity.ok(movimientoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoEntity> getById(@PathVariable Integer id) {
        return movimientoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody MovimientoRQ movimiento) {
        try {
            MovimientoEntity movimientoEntity = movimientoService.update(id, movimiento);
            return ResponseEntity.status(HttpStatus.OK).body(movimientoEntity);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        movimientoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
