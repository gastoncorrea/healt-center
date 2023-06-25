package centrosalud.inicio.service;

import centrosalud.inicio.dto.TurnosDisponiblesDTO;
import centrosalud.inicio.model.DisponibilidadTerapeuta;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnosDisponiblesDTOService implements ITurnosDisponiblesDTOService {

    @Autowired
    private IDisponibilidadTerapeutaService disponibilidadService;

    @Override
    public List<DisponibilidadTerapeuta> disponibilidadUnTerapeuta(Long id) {

        List<DisponibilidadTerapeuta> filtrarTerapeuta = new ArrayList<>();
        List<DisponibilidadTerapeuta> dispTerapeuta = disponibilidadService.todasLasDisponibilidades();

        for (DisponibilidadTerapeuta disp : dispTerapeuta) {
            if (disp.getTerapeuta().getId_terapeuta().equals(id)) {
                filtrarTerapeuta.add(disp);
            }
        }
        return filtrarTerapeuta;
    }

    @Override
    public List<LocalTime> cantidadTurnos(DisponibilidadTerapeuta disponibilidad) {
        List<LocalTime> cantidadTurnos = new ArrayList<>();
        LocalTime hora_inicio = disponibilidad.getHora_inicio();
        LocalTime hora_fin = disponibilidad.getHora_fin();

        LocalTime sumatoria = hora_inicio;

        while (sumatoria.isBefore(hora_fin)) {
            
            cantidadTurnos.add(sumatoria);
            sumatoria = sumatoria.plusMinutes(40);
        }

        return cantidadTurnos;
    }

    @Override
    public List<LocalDate> fechasProximas(DayOfWeek dia) {
        /*LocalDate fechaActual =  LocalDate.now();
        
        LocalDate fechaSiguiente = fechaActual.with(TemporalAdjusters.nextOrSame(dia));
        return fechaSiguiente; */

        return null;
    }

    @Override
    public List<TurnosDisponiblesDTO> cantidadTurnosPorDia(List<DisponibilidadTerapeuta> disponibilidadBruta) {
        List<TurnosDisponiblesDTO> disponibilidadNeta = new ArrayList<>();

        for (DisponibilidadTerapeuta disp : disponibilidadBruta) {
            TurnosDisponiblesDTO turno = new TurnosDisponiblesDTO();
            turno.setDia(disp.getDia().toString());
            turno.setFecha(this.fechaMasCercana(disp.getDia()));
            turno.setHora_inicio(disp.getHora_inicio());
            turno.setHora_fin(disp.getHora_fin());
            turno.setCantidadTurnos(this.cantidadTurnos(disp));

            disponibilidadNeta.add(turno);
        }

        return disponibilidadNeta;
    }

    @Override
    public List<LocalDate> fechaMasCercana(DayOfWeek diaSemana) {
        List<LocalDate> fechasCercanas = new ArrayList<>();
        LocalDate fechaActual = LocalDate.now();
        DayOfWeek diaActual = fechaActual.getDayOfWeek();

        int dias = 0;

        if (diaActual.getValue() < diaSemana.getValue()) {
            dias = diaSemana.getValue() - diaActual.getValue();
        } else {
            dias = 7 - (diaActual.getValue() - diaSemana.getValue());
        }
        
        for (int i = 0; i < 4; i++) {
            LocalDate fechaCercana = fechaActual.plusDays(dias + (7 * i));
            fechasCercanas.add(fechaCercana);
        }

        return fechasCercanas;
    }
}
