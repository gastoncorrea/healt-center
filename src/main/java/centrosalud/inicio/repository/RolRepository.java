
package centrosalud.inicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import centrosalud.inicio.model.Rol;


@Repository
public interface RolRepository extends JpaRepository<Rol,Long> {
    
}
