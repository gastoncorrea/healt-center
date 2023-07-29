
package centrosalud.inicio.service;

import centrosalud.inicio.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import centrosalud.inicio.repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {
    
    @Autowired
    private UsuarioRepository pacienteRepository;

    @Override
    public void crearPaciente(Usuario paciente) {
        
        pacienteRepository.save(paciente);
        
    }

    @Override
    public List<Usuario> traerTodosLosPacientes() {
        
        List<Usuario> listaPacientes = pacienteRepository.findAll();
        return listaPacientes;
        
    }

    @Override
    public Usuario encontrarUnPaciente(Long id) {
        
        Usuario pacienteEncontrado = pacienteRepository.findById(id).orElse(null);
        return pacienteEncontrado;
        
    }

    @Override
    public void borrarUnPaciente(Long id) {
        
        pacienteRepository.deleteById(id);
        
    }
    
    
}
