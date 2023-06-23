
package centrosalud.inicio.controller;

import centrosalud.inicio.model.Turnos;
import centrosalud.inicio.service.ITurnoService;
import java.time.LocalDate;
import java.time.LocalTime;
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
@CrossOrigin(origins="*")
@RequestMapping("/turno")
public class TurnoController {
    
    @Autowired
    private ITurnoService turnoService;
    
    @PostMapping("/save")
    public String guardarTurno(@RequestBody Turnos turno){
        turnoService.guardarTurno(turno);
        return "Turno guardado con exito";
    }
    
    @GetMapping("/all")
    public List<Turnos> listaTurnos(){
        List<Turnos> todosLosTurnos = turnoService.todosLosTurnos();
        return todosLosTurnos;
    }
    
    @GetMapping("/one/{id}")
    public Turnos unTurno(@PathVariable Long id){
        Turnos turno = turnoService.unTurno(id);
        return turno;
    }
    
    @PutMapping("/update/{id}")
    public Turnos actualizarTurno(@PathVariable Long id,
                                    @RequestParam("fecha") LocalDate nuevaFecha,
                                    @RequestParam("hora_inicio") LocalTime nuevaHora,
                                    @RequestParam("tiempo_consulta") LocalTime nuevoTiempoConsulta){
        
        Turnos turno = turnoService.unTurno(id);
        
        turno.setFecha(nuevaFecha);
        turno.setHora_inicio(nuevaHora);
        turno.setTiempo_consulta(nuevoTiempoConsulta);
        
        turnoService.guardarTurno(turno);
        
        return turno;
    }
    
    @DeleteMapping("/delete/{id}")
    public String borrarTurno(@PathVariable Long id){
        turnoService.eliminarUnturno(id);
        return "Turno eliminado";
    }
}       
