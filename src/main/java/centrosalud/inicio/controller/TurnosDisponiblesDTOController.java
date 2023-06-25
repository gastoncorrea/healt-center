
package centrosalud.inicio.controller;

import centrosalud.inicio.dto.TurnosDisponiblesDTO;
import centrosalud.inicio.model.DisponibilidadTerapeuta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import centrosalud.inicio.service.ITurnosDisponiblesDTOService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/turnos")
public class TurnosDisponiblesDTOController {
    
    @Autowired
    private ITurnosDisponiblesDTOService disponibilidadService;
    
    @GetMapping("/disponibilidad-terapeuta/{id}")
    public List<TurnosDisponiblesDTO> disponibilidadTerapeuta(@PathVariable Long id){
        
        List<DisponibilidadTerapeuta> disponibilidadBruta = disponibilidadService.disponibilidadUnTerapeuta(id);
        List<TurnosDisponiblesDTO> disponibilidadNeta = disponibilidadService.cantidadTurnosPorDia(disponibilidadBruta);
        
        
        return disponibilidadNeta;
    }
}
