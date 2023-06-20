
package centrosalud.inicio.service;

import centrosalud.inicio.model.Turnos;
import java.util.List;


public interface ITurnoService {
    
    public void guardarTurno(Turnos turno);
    
    public List<Turnos> todosLosTurnos();
    
    public Turnos unTurno(Long id);
    
    public void eliminarUnturno(Long id);
}
