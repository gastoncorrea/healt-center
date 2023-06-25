/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package centrosalud.inicio.service;

import centrosalud.inicio.dto.TurnosDisponiblesDTO;
import centrosalud.inicio.model.DisponibilidadTerapeuta;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author jugad
 */
public interface ITurnosDisponiblesDTOService {
    
    public List<DisponibilidadTerapeuta> disponibilidadUnTerapeuta(Long id);
    
    public List<LocalTime> cantidadTurnos(DisponibilidadTerapeuta disponibilidad);
    
    public List<LocalDate> fechasProximas(DayOfWeek dia);
    
    public List<TurnosDisponiblesDTO> cantidadTurnosPorDia(List<DisponibilidadTerapeuta> disponibilidadBruta);
    
    public List<LocalDate> fechaMasCercana(DayOfWeek dia);
}
