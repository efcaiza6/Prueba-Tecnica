package com.tcs.ptecnica.service;

import com.tcs.ptecnica.entity.ClienteEntity;
import com.tcs.ptecnica.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteEntity save(ClienteEntity clienteEntity) {
        if (clienteRepository.existsById(clienteEntity.getIdentificacion())) {
            throw new RuntimeException("Cliente con esa identificación ya existe");
        }
        return clienteRepository.save(clienteEntity);
    }

    public List<ClienteEntity> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteEntity> findById(String clienteId) {
        return clienteRepository.findById(clienteId);
    }

    public ClienteEntity update(String clienteId, ClienteEntity updatedCliente) {
        return clienteRepository.findById(clienteId)
                .map(cliente -> {
                    cliente.setNombre(updatedCliente.getNombre());
                    cliente.setGenero(updatedCliente.getGenero());
                    cliente.setEdad(updatedCliente.getEdad());
                    cliente.setIdentificacion(updatedCliente.getIdentificacion());
                    cliente.setDireccion(updatedCliente.getDireccion());
                    cliente.setTelefono(updatedCliente.getTelefono());
                    cliente.setContrasena(updatedCliente.getContrasena());
                    cliente.setEstado(updatedCliente.getEstado());
                    return clienteRepository.save(cliente);
                })
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + clienteId));
    }

    public void delete(String clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new RuntimeException("Cliente no encontrado con ID: " + clienteId);
        }
        clienteRepository.deleteById(clienteId);
    }
}
