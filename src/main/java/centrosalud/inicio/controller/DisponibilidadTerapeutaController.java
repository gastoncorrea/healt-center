
package centrosalud.inicio.controller;

import centrosalud.inicio.dto.TurnosDisponiblesDTO;
import centrosalud.inicio.model.DisponibilidadTerapeuta;
import centrosalud.inicio.service.IDisponibilidadTerapeutaService;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/disponibilidad")
public class DisponibilidadTerapeutaController {
    
    @Autowired
    private IDisponibilidadTerapeutaService disponibilidadService;
    
    @PostMapping("/save")
    public String nuevoTerapeuta(@RequestBody DisponibilidadTerapeuta disponibilidad){
        disponibilidadService.nuevaDisponibilidad(disponibilidad);
        return "Disponibilidad guardada con exito";
    }
    
    @GetMapping("/all")
    public List<DisponibilidadTerapeuta> todasLasDisponibilidades(){
        List<DisponibilidadTerapeuta> disponibilidadTerapeutas = disponibilidadService.todasLasDisponibilidades();
        return disponibilidadTerapeutas;
    }
    
    @PutMapping("/update/{id}")
    public DisponibilidadTerapeuta unaDisponibilidad(@PathVariable Long id,
                                                        @RequestParam("dia") String nuevoDia,
                                                        @RequestParam("hora_inicio") LocalTime nuevaHora,
                                                        @RequestParam("hora_fin") LocalTime nuevaHoraFin){
        
        DisponibilidadTerapeuta disponibilidad = disponibilidadService.unaDisponibilidad(id);
        disponibilidad.setDia(nuevoDia);
        disponibilidad.setHora_inicio(nuevaHora);
        disponibilidad.setHora_fin(nuevaHoraFin);
        
        disponibilidadService.nuevaDisponibilidad(disponibilidad);
        return disponibilidad;
    }
    
    @DeleteMapping("/delete/{id}")
    public String borrarUnaDisponibilidad(@PathVariable Long id){
        disponibilidadService.borrarUnaDisponibilidad(id);
        return "Disponibilidad Eliminada";
    }
    
    @GetMapping("/disponibilidad-terapeuta/{id}")
    public List<TurnosDisponiblesDTO> disponibilidadTerapeuta(@PathVariable Long id){
        
        List<DisponibilidadTerapeuta> disponibilidadBruta = disponibilidadService.disponibilidadUnTerapeuta(id);
        List<TurnosDisponiblesDTO> disponibilidadNeta = new ArrayList<>();
        
        
        for (DisponibilidadTerapeuta disp : disponibilidadBruta){
            TurnosDisponiblesDTO turno = new TurnosDisponiblesDTO();
            turno.setFecha(disp.getFecha());
            turno.setDia(disp.getDia());
            turno.setHora_inicio(disp.getHora_inicio());
            turno.setHora_fin(disp.getHora_fin());
            turno.setCantidadTurnos(disponibilidadService.cantidadTurnos(disp));
            
            disponibilidadNeta.add(turno);
        }
        
        return disponibilidadNeta;
    }
    
}
