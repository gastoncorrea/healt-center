
package centrosalud.inicio.service;

import centrosalud.inicio.model.Authorization;
import centrosalud.inicio.model.Rol;
import centrosalud.inicio.repository.AuthorizationRepository;
import java.util.List;
import java.util.Random;
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

    @Override
    public Authorization encontrarXEmail(String email) {
        Authorization unaAutorizacion = authRepo.findByEmail(email);
        return unaAutorizacion;
    }
    
    @Override
    public int generarCodigo(){
        Random randomNumber = new Random();
        int codigo = 1000 + randomNumber.nextInt(9000);
        return codigo;
    }
    
    
}
