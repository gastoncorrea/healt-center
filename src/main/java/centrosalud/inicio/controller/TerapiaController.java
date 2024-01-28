/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package centrosalud.inicio.controller;

import centrosalud.inicio.model.Terapia;
import centrosalud.inicio.service.ITerapiaService;
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
@RequestMapping("/terapia")
public class TerapiaController {
    
    @Autowired
    private ITerapiaService terapiaServ;
    
    @PostMapping("/save")
    public ResponseEntity<String> crearTerapia(@RequestBody Terapia terapia){
        
        terapiaServ.crearTerapia(terapia);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Terapia creada con exito");
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Terapia>> todasLasTerapias(){
        List<Terapia> todasLasTerapias = terapiaServ.todasLasTerapias();
        return ResponseEntity.ok(todasLasTerapias);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Terapia> modificarUnaTerapia(@PathVariable Long id,
            @RequestBody Terapia nuevaTerapia){
        
        Terapia modificarTerapia = terapiaServ.buscarUnaTerapia(id);
        if(modificarTerapia != null){
            modificarTerapia.setId(id);
            modificarTerapia.setLinkImagen(nuevaTerapia.getLinkImagen());
            modificarTerapia.setNombre(nuevaTerapia.getNombre());
            modificarTerapia.setDescripcion(nuevaTerapia.getDescripcion());
            terapiaServ.crearTerapia(modificarTerapia);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(modificarTerapia);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarUnaTerapia(@PathVariable Long id){
        
        terapiaServ.eliminarUnaTerapia(id);
        return ResponseEntity.ok("Terapia eliminada");
    }    
}
