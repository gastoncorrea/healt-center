
package centrosalud.inicio.service;

import centrosalud.inicio.model.DisponibilidadTerapeuta;
import java.util.List;


public interface IDisponibilidadTerapeutaService {
    
    public void nuevaDisponibilidad(DisponibilidadTerapeuta disponibilidad);
    
    public List<DisponibilidadTerapeuta> todasLasDisponibilidades();
    
    public DisponibilidadTerapeuta unaDisponibilidad(long id);
    
    public void borrarUnaDisponibilidad(Long id);
    
    public List<DisponibilidadTerapeuta> disponibilidadUnTerapeuta(Long id);
    
}
