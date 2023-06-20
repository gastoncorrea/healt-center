
package centrosalud.inicio.service;

import centrosalud.inicio.model.Turnos;
import centrosalud.inicio.repository.TurnoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnoService implements ITurnoService{
    
    @Autowired
    private TurnoRepository turnoRepository;
    
    @Override
    public void guardarTurno(Turnos turno) {
        turnoRepository.save(turno);
    }

    @Override
    public List<Turnos> todosLosTurnos() {
        List<Turnos> listaDeTurnos = turnoRepository.findAll();
        return listaDeTurnos;
    }

    @Override
    public Turnos unTurno(Long id) {
        Turnos encontrarTurno = turnoRepository.findById(id).orElse(null);
        return encontrarTurno;
    }

    @Override
    public void eliminarUnturno(Long id) {
        turnoRepository.deleteById(id);
    }
    
}
