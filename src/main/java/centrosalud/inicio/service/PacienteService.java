
package centrosalud.inicio.service;

import centrosalud.inicio.model.Paciente;
import centrosalud.inicio.repository.PacienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService implements IPacienteService {
    
    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public void crearPaciente(Paciente paciente) {
        
        pacienteRepository.save(paciente);
        
    }

    @Override
    public List<Paciente> traerTodosLosPacientes() {
        
        List<Paciente> listaPacientes = pacienteRepository.findAll();
        return listaPacientes;
        
    }

    @Override
    public Paciente encontrarUnPaciente(Long id) {
        
        Paciente pacienteEncontrado = pacienteRepository.findById(id).orElse(null);
        return pacienteEncontrado;
        
    }

    @Override
    public void borrarUnPaciente(Long id) {
        
        pacienteRepository.deleteById(id);
        
    }
    
    
}
