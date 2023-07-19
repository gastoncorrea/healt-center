
package centrosalud.inicio.controller;

import centrosalud.inicio.model.Terapeuta;
import centrosalud.inicio.service.ITerapeutaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/terapeuta")
public class TerapeutaController {
    
    @Autowired
    private ITerapeutaService terapeutaService;
    
    @PostMapping("/save")
    public String nuevoTerapeuta(@RequestBody Terapeuta terapeuta){       
        terapeutaService.nuevoTerapeuta(terapeuta);
        return "Terapeuta creado con exito";
    }
    
    @GetMapping("/all")
    public List<Terapeuta> listaDeTerapeutas(){
        List<Terapeuta> terapeutas = terapeutaService.todosLosTerapeutas();
        return terapeutas;
    }
    
    @GetMapping("/find/{id}")
    public  Terapeuta unTerapueta(@PathVariable Long id){
        Terapeuta terapeuta = terapeutaService.encontrarUnTerapeuta(id);
        return terapeuta;
    }
    
    @DeleteMapping("/delete/{id}")
    public String borrarTerapeuta(@PathVariable Long id){
        terapeutaService.eliminarUnTerapeuta(id);
        return "Terapeuta eliminado";
    }
    
    @PutMapping("/update/{id}")
    public Terapeuta modificarTerapeuta(@PathVariable Long id,
                                        @RequestParam("nombre") String nuevoNombre,
                                        @RequestParam("apellido")String nuevoApellido,
                                        @RequestParam("email")String nuevoEmail,
                                        @RequestParam("terapia") String nuevaTerapia){
        
        Terapeuta terapeuta = terapeutaService.encontrarUnTerapeuta(id);
        terapeuta.setNombre(nuevoNombre);
        terapeuta.setApellido(nuevoApellido);
        terapeuta.setEmail(nuevoEmail);
        terapeuta.setTerapia(nuevaTerapia);
        
        terapeutaService.nuevoTerapeuta(terapeuta);
        
        return terapeuta;
    }
}
