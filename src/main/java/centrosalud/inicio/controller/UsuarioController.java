package centrosalud.inicio.controller;

import centrosalud.inicio.dto.LoginDTO;
import centrosalud.inicio.model.Rol;
import centrosalud.inicio.model.Usuario;
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
import centrosalud.inicio.service.IUsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IRolService rolServ;
    
    @PostMapping("/save")
    public ResponseEntity<String> nuevoUsuario(@RequestBody Usuario usuario) {
        if (usuario.getRol().getId() != null) {
            usuarioService.crearPaciente(usuario);
        } else {
            Rol asignarRol = rolServ.encontrarUnRol(1L);
            usuario.getRol().setId(asignarRol.getId());
            usuarioService.crearPaciente(usuario);
        }
        
        return ResponseEntity.ok("Usuario creado con exito");
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> todosLosUsuarios() {
        List<Usuario> listaDePacientes = usuarioService.traerTodosLosPacientes();
        if (listaDePacientes != null) {
            return ResponseEntity.ok(listaDePacientes);
        } else {
            return ResponseEntity.badRequest().body(listaDePacientes);
        }
        
    }
    
    @GetMapping("/find/{id}")
    public ResponseEntity<Usuario> unUsuario(@PathVariable Long id) {
        Usuario paciente = usuarioService.encontrarUnPaciente(id);
        if (paciente != null) {
            return ResponseEntity.ok(paciente); // Respuesta con código de estado 200 (OK)
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> borrarUsuario(@PathVariable Long id) {
        usuarioService.borrarUnPaciente(id);
        return ResponseEntity.ok("Paciente eliminado con exito");
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Usuario> modificarUsuario(@PathVariable Long id,
            @RequestBody Usuario nuevoUsuario) {
        
        Usuario usuario = usuarioService.encontrarUnPaciente(id);
        if (usuario != null) {
            usuario.setNombre(nuevoUsuario.getNombre());
            usuario.setApellido(nuevoUsuario.getApellido());
            usuario.setNombreUsuario(nuevoUsuario.getNombreUsuario());
            usuario.setEmail(nuevoUsuario.getEmail());
            usuario.setPassword(nuevoUsuario.getPassword());
            
            usuarioService.crearPaciente(usuario);
            
            return ResponseEntity.ok(usuario);
        } else {
            Usuario pacienteError = new Usuario();
            String mensajeError = "El paciente con el ID " + id + " no existe.";
            pacienteError.setNombre(mensajeError);//llevo el error en el atributo nombre del objeto terapeuta
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(usuario);
        }
        
    }
    
    @PostMapping("/login")
    public ResponseEntity<Boolean> validarCredenciales(@RequestBody LoginDTO usuario){
        
        String nombreEmailUsuario = usuario.getNombreEmailUsuario();
        String password = usuario.getPassword();
        
        Usuario findUsuario = usuarioService.encontrarXNombreDeUsuario(nombreEmailUsuario);
        if(findUsuario != null && findUsuario.getPassword().equals(password)){
            return ResponseEntity.ok(true);
        }else{
            Usuario findUsuarioEmail = usuarioService.encontrarXEmail(nombreEmailUsuario);
            if(findUsuarioEmail != null && findUsuarioEmail.getPassword().equals(password)){
                return ResponseEntity.ok(true);
            }
        }
        return ResponseEntity.badRequest().body(false);
    } 
}
