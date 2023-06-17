
package centrosalud.inicio.repository;

import centrosalud.inicio.model.Terapeuta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerapeutaRepository extends JpaRepository<Terapeuta,Long>{
    
}
