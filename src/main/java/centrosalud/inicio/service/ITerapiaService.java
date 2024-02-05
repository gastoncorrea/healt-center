
package centrosalud.inicio.service;

import centrosalud.inicio.model.Terapia;
import java.util.List;


public interface ITerapiaService {
    
    public void crearTerapia(Terapia terapia);
    
    public List<Terapia> todasLasTerapias();
    
    public Terapia buscarUnaTerapia(Long id);
    
    public void eliminarUnaTerapia(Long id);
    
}
