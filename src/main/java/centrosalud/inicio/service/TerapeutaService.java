
package centrosalud.inicio.service;

import centrosalud.inicio.model.Terapeuta;
import centrosalud.inicio.repository.TerapeutaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerapeutaService implements ITerapeutaService {
    
    @Autowired
    private TerapeutaRepository terapeutaRepository;

    @Override
    public void nuevoTerapeuta(Terapeuta terapeuta) {
        terapeutaRepository.save(terapeuta);
    }

    @Override
    public List<Terapeuta> todosLosTerapeutas() {
        List<Terapeuta> listaTerapeutas = terapeutaRepository.findAll();
        return listaTerapeutas;
    }

    @Override
    public Terapeuta encontrarUnTerapeuta(Long id) {
        Terapeuta unTerapeuta = terapeutaRepository.findById(id).orElse(null);
        return unTerapeuta;
    }

    @Override
    public void eliminarUnTerapeuta(Long id) {
        terapeutaRepository.deleteById(id);
    }
    
}
