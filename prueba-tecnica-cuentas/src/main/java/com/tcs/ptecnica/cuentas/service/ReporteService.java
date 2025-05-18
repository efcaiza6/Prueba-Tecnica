package com.tcs.ptecnica.cuentas.service;

import com.tcs.ptecnica.cuentas.dto.ClienteRS;
import com.tcs.ptecnica.cuentas.dto.ReporteRS;
import com.tcs.ptecnica.cuentas.entity.CuentaEntity;
import com.tcs.ptecnica.cuentas.entity.MovimientoEntity;
import com.tcs.ptecnica.cuentas.repository.CuentaRepository;
import com.tcs.ptecnica.cuentas.repository.MovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteService {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;
    private final RestTemplate restTemplate;

    @Value("${cliente.service.url}")
    private String clienteServiceUrl;

    /**
     * Genera un reporte de movimientos de un cliente en un rango de fechas.
     *
     * @param clienteId El id del cliente.
     * @param fechaInicio El inicio del rango de fechas.
     * @param fechaFin El fin del rango de fechas.
     *
     * @return Un listado de objetos {@link ReporteRS} con los movimientos.
     *
     * @throws RuntimeException si el cliente no existe, no tiene cuentas o no se encontraron movimientos en el rango de fechas.
     */
    public List<ReporteRS> generarReporte(String clienteId, LocalDate fechaInicio, LocalDate fechaFin) {

        // 1. Obtener cliente desde otro microservicio
        ClienteRS cliente = restTemplate.getForObject(
                clienteServiceUrl + clienteId,
                ClienteRS.class
        );

        if (cliente == null) {
            throw new RuntimeException("El cliente no existe");
        }

        // 2. Obtener cuentas del cliente
        List<CuentaEntity> cuentas = cuentaRepository.findByClienteId(clienteId);

        if(cuentas.isEmpty()) {
            throw new RuntimeException("El cliente no tiene cuentas");
        }

        List<ReporteRS> resultado = new ArrayList<>();

        for (CuentaEntity cuenta : cuentas) {

            // 3. Buscar movimientos de esa cuenta
            List<MovimientoEntity> movimientos = movimientoRepository
                    .findByCuentaAndFechaBetween(
                            cuenta,
                            fechaInicio.atStartOfDay(),
                            fechaFin.atTime(LocalTime.MAX)
                    );

            for (MovimientoEntity mov : movimientos) {

                ReporteRS dto = new ReporteRS();
                dto.setFecha(mov.getFecha().toLocalDate().toString());
                assert cliente != null;
                dto.setClienteNombre(cliente.getNombre());
                dto.setNumeroCuenta(cuenta.getNumeroCuenta());
                dto.setTipoCuenta(cuenta.getTipoCuenta());
                dto.setSaldoInicial(mov.getSaldo()-mov.getValor());
                dto.setEstadoCuenta(cuenta.getEstado());

                String tipo = mov.getValor() < 0 ? "Retiro" : "Depósito";
                dto.setTipoMovimiento(tipo + " de " + Math.abs(mov.getValor()));

                dto.setSaldoFinal(mov.getSaldo());

                resultado.add(dto);
            }
        }
        if (resultado.isEmpty()) {
            throw new RuntimeException("No se encontraron movimientos en el rango de fechas");
        }
        return resultado;
    }
}
