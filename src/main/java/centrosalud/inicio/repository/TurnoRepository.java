
package centrosalud.inicio.repository;

import centrosalud.inicio.model.Turnos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turnos, Long> {
    
}
