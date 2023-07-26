package centrosalud.inicio.controller;

import centrosalud.inicio.model.Paciente;
import centrosalud.inicio.service.IPacienteService;
import java.time.LocalDate;
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
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private IPacienteService pacienteService;

    @PostMapping("/save")
    public ResponseEntity<String> nuevoPaciente(@RequestBody Paciente paciente) {
        pacienteService.crearPaciente(paciente);
        return ResponseEntity.ok("Paciente creado con exito");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Paciente>> todosLosPacientes() {
        List<Paciente> listaDePacientes = pacienteService.traerTodosLosPacientes();
        if (listaDePacientes != null) {
            return ResponseEntity.ok(listaDePacientes);
        } else {
            return ResponseEntity.badRequest().body(listaDePacientes);
        }

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Paciente> unPaciente(@PathVariable Long id) {
        Paciente paciente = pacienteService.encontrarUnPaciente(id);
        if (paciente != null) {
            return ResponseEntity.ok(paciente); // Respuesta con código de estado 200 (OK)
        } else {
            Paciente pacienteError = new Paciente();
            String mensajeError = "El paciente con el ID " + id + " no existe.";
            pacienteError.setNombre(mensajeError);//llevo el error en el atributo nombre del objeto terapeuta
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(paciente);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> borrarPaciente(@PathVariable Long id) {
        pacienteService.borrarUnPaciente(id);
        return ResponseEntity.ok("Paciente eliminado con exito");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Paciente> modificarPaciente(@PathVariable Long id,
            @RequestParam("nombre") String nuevoNombre,
            @RequestParam("apellido") String nuevoApellido,
            @RequestParam("fecha_nac") LocalDate nuevaFecha,
            @RequestParam("dni") int nuevoDni,
            @RequestParam("email") String nuevoEmail,
            @RequestParam("telefono") int nuevoTelefono) {

        Paciente paciente = pacienteService.encontrarUnPaciente(id);
        if (paciente != null) {
            paciente.setNombre(nuevoNombre);
            paciente.setApellido(nuevoApellido);
            paciente.setFecha_nac(nuevaFecha);
            paciente.setDni(nuevoDni);
            paciente.setEmail(nuevoEmail);
            paciente.setTelefono(nuevoTelefono);

            pacienteService.crearPaciente(paciente);

            return ResponseEntity.ok(paciente);
        } else {
            Paciente pacienteError = new Paciente();
            String mensajeError = "El paciente con el ID " + id + " no existe.";
            pacienteError.setNombre(mensajeError);//llevo el error en el atributo nombre del objeto terapeuta
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(paciente);
        }

    }
}
