/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class TurnosDisponiblesDTOService implements ITurnosDisponiblesDTOService{
    
    @Autowired
    private IDisponibilidadTerapeutaService disponibilidadService;
    
    
    @Override
    public List<DisponibilidadTerapeuta> disponibilidadUnTerapeuta(Long id) {
        
        List<DisponibilidadTerapeuta> filtrarTerapeuta = new ArrayList<>();
        List<DisponibilidadTerapeuta> dispTerapeuta = disponibilidadService.todasLasDisponibilidades();
        
        for(DisponibilidadTerapeuta disp : dispTerapeuta){
        if(disp.getTerapeuta().getId_terapeuta().equals(id))
            filtrarTerapeuta.add(disp);
        }
        return filtrarTerapeuta;
    }
    
    @Override
    public List<LocalTime> cantidadTurnos(DisponibilidadTerapeuta disponibilidad){
        List<LocalTime> cantidadTurnos = new ArrayList<>();
        LocalTime hora_inicio = disponibilidad.getHora_inicio();
        LocalTime hora_fin = disponibilidad.getHora_fin();
        
        LocalTime sumatoria = hora_inicio;
        cantidadTurnos.add(sumatoria);
        
        while(sumatoria.isBefore(hora_fin)){
            sumatoria = sumatoria.plusMinutes(40);
            cantidadTurnos.add(sumatoria);
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
        
        for (DisponibilidadTerapeuta disp : disponibilidadBruta){
            TurnosDisponiblesDTO turno = new TurnosDisponiblesDTO();
            turno.setFecha(disp.getFecha());
            turno.setDia(disp.getDia());
            turno.setHora_inicio(disp.getHora_inicio());
            turno.setHora_fin(disp.getHora_fin());
            turno.setCantidadTurnos(this.cantidadTurnos(disp));
            
            disponibilidadNeta.add(turno);
        }
        
        return disponibilidadNeta;
    }
}
