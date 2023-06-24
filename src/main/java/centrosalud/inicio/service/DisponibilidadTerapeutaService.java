
package centrosalud.inicio.service;

import centrosalud.inicio.model.DisponibilidadTerapeuta;
import centrosalud.inicio.repository.DisponibilidadTerapeutaRepository;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisponibilidadTerapeutaService implements IDisponibilidadTerapeutaService{
    
    @Autowired
    private DisponibilidadTerapeutaRepository disponibilidadRepository;

    @Override
    public void nuevaDisponibilidad(DisponibilidadTerapeuta disponibilidad) {
        disponibilidadRepository.save(disponibilidad);
    }

    @Override
    public List<DisponibilidadTerapeuta> todasLasDisponibilidades() {
        List<DisponibilidadTerapeuta> todasLasDisponibilidades = disponibilidadRepository.findAll();
        return todasLasDisponibilidades;
    }

    @Override
    public DisponibilidadTerapeuta unaDisponibilidad(long id) {
        DisponibilidadTerapeuta encontrarDisponibilidad = disponibilidadRepository.findById(id).orElse(null);
        return encontrarDisponibilidad;
    }   

    @Override
    public void borrarUnaDisponibilidad(Long id) {
        disponibilidadRepository.deleteById(id);
    }

    @Override
    public List<DisponibilidadTerapeuta> disponibilidadUnTerapeuta(Long id) {
        
        List<DisponibilidadTerapeuta> filtrarTerapeuta = new ArrayList<>();
        List<DisponibilidadTerapeuta> dispTerapeuta = todasLasDisponibilidades();
        
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
    
}
