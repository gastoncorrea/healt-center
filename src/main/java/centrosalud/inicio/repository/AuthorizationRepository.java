
package centrosalud.inicio.repository;

import centrosalud.inicio.model.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationRepository extends JpaRepository<Authorization, Long> {
    Authorization findByEmail(String email);
}
