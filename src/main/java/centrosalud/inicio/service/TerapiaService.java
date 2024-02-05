/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package centrosalud.inicio.service;

import centrosalud.inicio.model.Terapia;
import centrosalud.inicio.repository.TerapiaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerapiaService implements ITerapiaService {
    
    @Autowired
    private TerapiaRepository terapiaRep;

    @Override
    public void crearTerapia(Terapia terapia) {
        terapiaRep.save(terapia);
    }

    @Override
    public List<Terapia> todasLasTerapias() {
        List<Terapia> todasLasTerapias = terapiaRep.findAll();
        return todasLasTerapias;
    }

    @Override
    public Terapia buscarUnaTerapia(Long id) {
        Terapia unaTerapia = terapiaRep.findById(id).orElse(null);
        return unaTerapia;
    }

    @Override
    public void eliminarUnaTerapia(Long id) {
        terapiaRep.deleteById(id);
    }
    
}
