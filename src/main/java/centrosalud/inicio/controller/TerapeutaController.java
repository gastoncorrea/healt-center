package centrosalud.inicio.controller;

import centrosalud.inicio.model.Terapeuta;
import centrosalud.inicio.service.ITerapeutaService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/terapeuta")
public class TerapeutaController {

    @Autowired
    private ITerapeutaService terapeutaService;

    @PostMapping("/save")
    public ResponseEntity<String> nuevoTerapeuta(@RequestBody Terapeuta terapeuta) {
        terapeutaService.nuevoTerapeuta(terapeuta);
        return ResponseEntity.status(HttpStatus.CREATED).body("Terapeuta creado con exito");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Terapeuta>> listaDeTerapeutas() {
        List<Terapeuta> terapeutas = terapeutaService.todosLosTerapeutas();
        return ResponseEntity.ok(terapeutas);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Terapeuta> unTerapueta(@PathVariable Long id) {
        Terapeuta terapeuta = terapeutaService.encontrarUnTerapeuta(id);
        if (terapeuta != null) {
            return ResponseEntity.ok(terapeuta); // Respuesta con código de estado 200 (OK)
        } else {
            Terapeuta terapeutaError = new Terapeuta();
            String mensajeError = "El terapeuta con el ID " + id + " no existe.";
            terapeutaError.setNombre(mensajeError);//llevo el error en el atributo nombre del objeto terapeuta
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(terapeuta);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> borrarTerapeuta(@PathVariable Long id) {
        terapeutaService.eliminarUnTerapeuta(id);
        return ResponseEntity.ok("Terapeuta eliminado");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Terapeuta> modificarTerapeuta(@PathVariable Long id,
            @RequestBody Terapeuta nuevoTerapeuta) {

        Terapeuta terapeuta = terapeutaService.encontrarUnTerapeuta(id);
        if (terapeuta != null) {
            terapeuta.setNombre(nuevoTerapeuta.getNombre());
            terapeuta.setApellido(nuevoTerapeuta.getApellido());
            terapeuta.setProfesion_idTerapia(nuevoTerapeuta.getProfesion_idTerapia());
            terapeuta.setImagen_perfil(nuevoTerapeuta.getImagen_perfil());
            terapeuta.setEmail(nuevoTerapeuta.getEmail());

            terapeutaService.nuevoTerapeuta(terapeuta);
            return ResponseEntity.ok(terapeuta);
        } else {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(terapeuta);
        }

    }
}
