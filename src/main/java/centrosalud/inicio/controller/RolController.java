
package centrosalud.inicio.controller;

import centrosalud.inicio.model.Rol;
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
@CrossOrigin(origins="*")
@RequestMapping("/rol")
public class RolController {
    
    @Autowired
    private IRolService rolService;
    
    @PostMapping("/save")
    public ResponseEntity<String> guardarRol(@RequestBody Rol rol){
        rolService.nuevoRol(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body("Nuevo rol creado con exito");
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Rol>> listaDeRoles(){
        List<Rol> listaDeRoles = rolService.listaDeRoles();
        return ResponseEntity.ok(listaDeRoles);
     }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> borrarRol(@PathVariable Long id) {
        rolService.eliminarRol(id);
        return ResponseEntity.ok("Terapeuta eliminado");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Rol> modificarRol(@PathVariable Long id,
            @RequestBody Rol nuevoRol) {

        Rol rol = rolService.encontrarUnRol(id);
        if (rol != null) {
            rol.setNombreRol(nuevoRol.getNombreRol());

            rolService.nuevoRol(rol);
            return ResponseEntity.ok(rol);
        } else {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }
}
