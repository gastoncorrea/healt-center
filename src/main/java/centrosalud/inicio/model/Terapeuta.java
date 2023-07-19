
package centrosalud.inicio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Blob;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "terapeutas")
public class Terapeuta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_terapeuta;
    @Column(columnDefinition = "VARCHAR(50)")
    private String nombre;
    @Column(columnDefinition = "VARCHAR(50)")
    private String apellido;
    @Column(columnDefinition = "VARCHAR(300)")
    private String descripcion_terapia;
    @Lob // Indica que el atributo puede almacenar datos grandes (como imágenes)
    @Column(name = "imagen_perfil", columnDefinition = "LONGBLOB")
    private byte[] imagen_perfil;
    @Column(columnDefinition = "VARCHAR(50)")
    private String email;
    @Column(columnDefinition = "VARCHAR(50)")
    private String terapia;
    
    @OneToMany(mappedBy = "terapeuta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<DisponibilidadTerapeuta> disponibilidades;
    
    @OneToMany(mappedBy = "terapeuta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Turnos> turnos;
}
