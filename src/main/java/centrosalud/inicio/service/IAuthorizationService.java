
package centrosalud.inicio.service;

import centrosalud.inicio.model.Authorization;
import java.util.List;


public interface IAuthorizationService {
    
    public void crearAutorizacion(Authorization nuevaAutorizacion);
    
    public List<Authorization> mostrarTodasLasAutorizaciones();
    
    public Authorization encontrarUnaAutorizacion(Long id);
    
    public void eliminarUnaAutorizacion(Long id);
    
    public Authorization encontrarXEmail(String email);
    
    public int generarCodigo();
    
}
