
package centrosalud.inicio.service;

import centrosalud.inicio.model.Paciente;
import java.util.List;


public interface IPacienteService {
    
    public void crearPaciente(Paciente paciente);
    
    public List<Paciente> traerTodosLosPacientes();
    
    public Paciente encontrarUnPaciente(Long id);
    
    public void borrarUnPaciente(Long id);
}
