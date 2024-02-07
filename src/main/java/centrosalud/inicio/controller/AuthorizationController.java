
package centrosalud.inicio.controller;

import centrosalud.inicio.model.Authorization;
import centrosalud.inicio.service.IAuthorizationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/auth")
public class AuthorizationController {
    
    @Autowired
    private IAuthorizationService authServ;
    
    @PostMapping("/save")
    public ResponseEntity<String> guardarAutorizacion(@RequestBody Authorization auth){
        authServ.crearAutorizacion(auth);
        return ResponseEntity.status(HttpStatus.CREATED ).body("Nueva autorizacion creada con exito");
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Authorization>> todasLasAutorizaciones(){
        List<Authorization> listaDeAutorizaciones = authServ.mostrarTodasLasAutorizaciones();
        return ResponseEntity.ok(listaDeAutorizaciones);
    }
    
}
