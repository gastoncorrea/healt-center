
package centrosalud.inicio.controller;

import centrosalud.inicio.model.Authorization;
import centrosalud.inicio.service.IAuthorizationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
    @GetMapping("/find/{id}")
    public ResponseEntity<Authorization> unAuth(@PathVariable Long id) {
        Authorization unaAutorizacion = authServ.encontrarUnaAutorizacion(id);
        if (unaAutorizacion != null) {
            return ResponseEntity.ok(unaAutorizacion); // Respuesta con código de estado 200 (OK)
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> borrarAuth(@PathVariable Long id) {
        authServ.eliminarUnaAutorizacion(id);
        return ResponseEntity.ok("Paciente eliminado con exito");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Authorization> modificarAuth(@PathVariable Long id,
            @RequestBody Authorization nuevaAuth) {

        Authorization unaAutorizacion = authServ.encontrarUnaAutorizacion(id);
        if (unaAutorizacion != null) {
           
            unaAutorizacion.setEmail(nuevaAuth.getEmail());
            unaAutorizacion.setCodigo(nuevaAuth.getCodigo());
            unaAutorizacion.getRol().setId(nuevaAuth.getRol().getId());

            authServ.crearAutorizacion(unaAutorizacion);
            
            return ResponseEntity.ok(unaAutorizacion);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    
}
