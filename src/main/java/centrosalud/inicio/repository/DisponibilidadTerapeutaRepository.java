
package centrosalud.inicio.repository;

import centrosalud.inicio.model.DisponibilidadTerapeuta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisponibilidadTerapeutaRepository extends JpaRepository<DisponibilidadTerapeuta,Long> {
  
}
