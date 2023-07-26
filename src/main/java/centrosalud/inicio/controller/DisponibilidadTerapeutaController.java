package centrosalud.inicio.controller;

import centrosalud.inicio.model.DisponibilidadTerapeuta;
import centrosalud.inicio.service.IDisponibilidadTerapeutaService;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> nuevoTerapeuta(@RequestBody DisponibilidadTerapeuta disponibilidad) {
        disponibilidadService.nuevaDisponibilidad(disponibilidad);
        return ResponseEntity.status(HttpStatus.CREATED).body("Disponibilidad del terapeuta guardada con exito");
    }

    @GetMapping("/all")
    public List<DisponibilidadTerapeuta> todasLasDisponibilidades() {
        List<DisponibilidadTerapeuta> disponibilidadTerapeutas = disponibilidadService.todasLasDisponibilidades();
        return disponibilidadTerapeutas;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DisponibilidadTerapeuta> unaDisponibilidad(@PathVariable Long id,
            @RequestParam("dia") DayOfWeek nuevoDia,
            @RequestParam("hora_inicio") LocalTime nuevaHora,
            @RequestParam("hora_fin") LocalTime nuevaHoraFin) {

        DisponibilidadTerapeuta disponibilidad = disponibilidadService.unaDisponibilidad(id);
        if (disponibilidad != null) {
            disponibilidad.setDia(nuevoDia);
            disponibilidad.setHora_inicio(nuevaHora);
            disponibilidad.setHora_fin(nuevaHoraFin);

            disponibilidadService.nuevaDisponibilidad(disponibilidad);
            return ResponseEntity.ok(disponibilidad);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/delete/{id}")
    public String borrarUnaDisponibilidad(@PathVariable Long id) {
        disponibilidadService.borrarUnaDisponibilidad(id);
        return "Disponibilidad Eliminada";
    }

}
