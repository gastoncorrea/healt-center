
package centrosalud.inicio.service;

import centrosalud.inicio.model.DisponibilidadTerapeuta;
import centrosalud.inicio.repository.DisponibilidadTerapeutaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisponibilidadTerapeutaService implements IDisponibilidadTerapeutaService{
    
    @Autowired
    private DisponibilidadTerapeutaRepository disponibilidadRepository;

    @Override
    public void nuevaDisponibilidad(DisponibilidadTerapeuta disponibilidad) {
        disponibilidadRepository.save(disponibilidad);
    }

    @Override
    public List<DisponibilidadTerapeuta> todasLasDisponibilidades() {
        List<DisponibilidadTerapeuta> todasLasDisponibilidades = disponibilidadRepository.findAll();
        return todasLasDisponibilidades;
    }

    @Override
    public DisponibilidadTerapeuta unaDisponibilidad(long id) {
        DisponibilidadTerapeuta encontrarDisponibilidad = disponibilidadRepository.findById(id).orElse(null);
        return encontrarDisponibilidad;
    }   

    @Override
    public void borrarUnaDisponibilidad(Long id) {
        disponibilidadRepository.deleteById(id);
    }

    
}
