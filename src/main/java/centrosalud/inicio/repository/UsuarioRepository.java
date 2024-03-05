
package centrosalud.inicio.repository;

import centrosalud.inicio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    Usuario findByNombreUsuario(String nombreUsuario);
    Usuario findByEmail(String email);
}
