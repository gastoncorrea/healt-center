
package centrosalud.inicio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_usuario;
    @Column(columnDefinition = "VARCHAR(50)")
    private String nombre;
    @Column(columnDefinition = "VARCHAR(50)")
    private String apellido;
    @Column(unique = true , columnDefinition = "VARCHAR(50)", nullable = false)
    private String nombreUsuario;
    @Column(unique = true , columnDefinition = "VARCHAR(50)" , nullable = false)
    private String email;
    @Column(columnDefinition = "VARCHAR(50)" , nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name="rol_id")
    private Rol rol;
}
