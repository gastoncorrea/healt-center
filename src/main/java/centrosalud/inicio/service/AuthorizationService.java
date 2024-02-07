
package centrosalud.inicio.service;

import centrosalud.inicio.model.Authorization;
import centrosalud.inicio.repository.AuthorizationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthorizationService implements IAuthorizationService {
    
    @Autowired
    private AuthorizationRepository authRepo;
    
    @Override
    public void crearAutorizacion(Authorization nuevaAutorizacion) {
        authRepo.save(nuevaAutorizacion);
    }

    @Override
    public List<Authorization> mostrarTodasLasAutorizaciones() {
        List<Authorization> listaDeAutorizaciones = authRepo.findAll();
        return listaDeAutorizaciones;
    }

    @Override
    public Authorization encontrarUnaAutorizacion(Long id) {
        Authorization unaAutorizacion = authRepo.findById(id).orElse(null);
        return unaAutorizacion;
    }

    @Override
    public void eliminarUnaAutorizacion(Long id) {
        authRepo.deleteById(id);
    }
    
    
}
