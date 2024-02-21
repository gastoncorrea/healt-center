package centrosalud.inicio.controller;

import centrosalud.inicio.model.Authorization;
import centrosalud.inicio.model.Rol;
import centrosalud.inicio.service.IAuthorizationService;
import centrosalud.inicio.service.IMailService;
import centrosalud.inicio.service.IRolService;
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
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthorizationController {

    @Autowired
    private IAuthorizationService authServ;
    @Autowired
    private IRolService rolServ;
    @Autowired
    private IMailService mailServ;

    @PostMapping("/save")
    public ResponseEntity<String> guardarAutorizacion(@RequestBody Authorization auth) {
        authServ.crearAutorizacion(auth);
        return ResponseEntity.status(HttpStatus.CREATED).body("Nueva autorizacion creada con exito");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Authorization>> todasLasAutorizaciones() {
        List<Authorization> listaDeAutorizaciones = authServ.mostrarTodasLasAutorizaciones();
        if(listaDeAutorizaciones != null){
            return ResponseEntity.ok(listaDeAutorizaciones);
        }else {
            return ResponseEntity.notFound().build();
        }
        
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
        return ResponseEntity.ok("Autorización eliminada con exito");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Authorization> modificarAuth(@PathVariable Long id,
            @RequestBody Authorization nuevaAuth) {

        Authorization unaAutorizacion = authServ.encontrarUnaAutorizacion(id);
        
        if (unaAutorizacion != null) {
            if (!nuevaAuth.getRol().getId().equals(unaAutorizacion.getId())) {
                Rol encontrarUnRol = rolServ.encontrarUnRol(nuevaAuth.getRol().getId());
                if (encontrarUnRol != null) {
                    unaAutorizacion.setRol(encontrarUnRol);
                }
            }

            unaAutorizacion.setEmail(nuevaAuth.getEmail());
            unaAutorizacion.setCodigo(nuevaAuth.getCodigo());

            authServ.crearAutorizacion(unaAutorizacion);

            return ResponseEntity.ok(unaAutorizacion);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    
    @GetMapping("/autorization/{email}")
    public ResponseEntity<Boolean> devolverCodigo(@PathVariable String email){
        Authorization unaAutorizacion = authServ.encontrarXEmail(email);
        if(unaAutorizacion != null){
            int codigo = authServ.generarCodigo();
            unaAutorizacion.setCodigo(codigo);
            authServ.crearAutorizacion(unaAutorizacion);
            mailServ.sendMail(email, codigo);
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.badRequest().body(false);
        }
        
    }

}
