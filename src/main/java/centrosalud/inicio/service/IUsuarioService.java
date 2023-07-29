
package centrosalud.inicio.service;

import centrosalud.inicio.model.Usuario;
import java.util.List;


public interface IUsuarioService {
    
    public void crearPaciente(Usuario paciente);
    
    public List<Usuario> traerTodosLosPacientes();
    
    public Usuario encontrarUnPaciente(Long id);
    
    public void borrarUnPaciente(Long id);
}
