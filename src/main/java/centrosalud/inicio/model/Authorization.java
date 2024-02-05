
package centrosalud.inicio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "autorizaciones")
public class Authorization {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    @Column(columnDefinition="VARCHAR(50)")
    private String email;
    @Column(columnDefinition = "INTEGER")
    private int codigo;
    
    //private Rol rol;
    
}
