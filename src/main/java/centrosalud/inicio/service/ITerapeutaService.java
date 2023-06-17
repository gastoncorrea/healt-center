
package centrosalud.inicio.service;

import centrosalud.inicio.model.Terapeuta;
import java.util.List;


public interface ITerapeutaService {
    
    public void nuevoTerapeuta(Terapeuta terapeuta);
    
    public List<Terapeuta> todosLosTerapeutas();
    
    public Terapeuta encontrarUnTerapeuta(Long id);
    
    public void eliminarUnTerapeuta(Long id);
}
