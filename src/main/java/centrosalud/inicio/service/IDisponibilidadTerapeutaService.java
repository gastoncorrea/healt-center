
package centrosalud.inicio.service;

import centrosalud.inicio.dto.TurnosDisponiblesDTO;
import centrosalud.inicio.model.DisponibilidadTerapeuta;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public interface IDisponibilidadTerapeutaService {
    
    public void nuevaDisponibilidad(DisponibilidadTerapeuta disponibilidad);
    
    public List<DisponibilidadTerapeuta> todasLasDisponibilidades();
    
    public DisponibilidadTerapeuta unaDisponibilidad(long id);
    
    public void borrarUnaDisponibilidad(Long id);
    
    public List<DisponibilidadTerapeuta> disponibilidadUnTerapeuta(Long id);
    
    public List<LocalTime> cantidadTurnos(DisponibilidadTerapeuta disponibilidad);
    
    public List<LocalDate> fechasProximas(DayOfWeek dia);
    
}
