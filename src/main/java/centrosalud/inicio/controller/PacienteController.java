
package centrosalud.inicio.controller;

import centrosalud.inicio.model.Paciente;
import centrosalud.inicio.service.IPacienteService;
import java.time.LocalDate;
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
@RequestMapping("/paciente")
public class PacienteController {
    
     @Autowired
     private IPacienteService pacienteService;
            
     @PostMapping("/save")
     public String nuevoPaciente(@RequestBody Paciente paciente){
         pacienteService.crearPaciente(paciente);
         return "Paciente creado con exito";
     }
     
     @GetMapping("/all")
     public List<Paciente> todosLosPacientes(){
         List<Paciente> listaDePacientes = pacienteService.traerTodosLosPacientes();
         return listaDePacientes;
     }
     
     @GetMapping("/find/{id}")
     public Paciente unPaciente(@PathVariable Long id){
         Paciente paciente = pacienteService.encontrarUnPaciente(id);
         return paciente;
     }
     
     @DeleteMapping("/delete/{id}")
     public String borrarPaciente(@PathVariable Long id){
         pacienteService.borrarUnPaciente(id);
         return "Paciente eliminado con exito";
     }
     
     @PutMapping("/update/{id}")
     public Paciente modificarPaciente(@PathVariable Long id,
                                        @RequestParam("nombre") String nuevoNombre,
                                        @RequestParam("apellido") String nuevoApellido,
                                        @RequestParam("fecha_nac") LocalDate nuevaFecha,
                                        @RequestParam("dni") int nuevoDni,
                                        @RequestParam("email") String nuevoEmail,
                                        @RequestParam("telefono") int nuevoTelefono){
         
         Paciente paciente = pacienteService.encontrarUnPaciente(id);
         paciente.setNombre(nuevoNombre);
         paciente.setApellido(nuevoApellido);
         paciente.setFecha_nac(nuevaFecha);
         paciente.setDni(nuevoDni);
         paciente.setEmail(nuevoEmail);
         paciente.setTelefono(nuevoTelefono);
         
         pacienteService.crearPaciente(paciente);
         
         return paciente;
    }
}
