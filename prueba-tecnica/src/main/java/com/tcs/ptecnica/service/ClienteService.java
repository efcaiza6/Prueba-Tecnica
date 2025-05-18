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

    /**
     * Guarda un nuevo cliente en la base de datos.
     * Lanza excepción si ya existe un cliente con la misma identificación.
     *
     * @param clienteEntity entidad cliente a guardar
     * @return cliente guardado
     */
    public ClienteEntity save(ClienteEntity clienteEntity) {
        if (clienteRepository.existsById(clienteEntity.getIdentificacion())) {
            throw new RuntimeException("Cliente con esa identificación ya existe");
        }
        return clienteRepository.save(clienteEntity);
    }

    /**
     * Obtiene la lista de todos los clientes.
     *
     * @return lista de clientes
     */
    public List<ClienteEntity> findAll() {
        return clienteRepository.findAll();
    }

    /**
     * Busca un cliente por su ID (identificación).
     *
     * @param clienteId identificación del cliente
     * @return Optional con el cliente encontrado o vacío si no existe
     */
    public Optional<ClienteEntity> findById(String clienteId) {
        return clienteRepository.findById(clienteId);
    }

    /**
     * Actualiza los datos de un cliente existente.
     * Lanza excepción si el cliente no existe.
     *
     * @param clienteId identificación del cliente a actualizar
     * @param updatedCliente entidad con los datos actualizados
     * @return cliente actualizado
     */
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

    /**
     * Elimina un cliente por su identificación.
     * Lanza excepción si el cliente no existe.
     *
     * @param clienteId identificación del cliente a eliminar
     */
    public void delete(String clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new RuntimeException("Cliente no encontrado con ID: " + clienteId);
        }
        clienteRepository.deleteById(clienteId);
    }
}
