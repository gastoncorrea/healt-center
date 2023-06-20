
package centrosalud.inicio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="turnos")
public class Turnos {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_turno;
    @Column(columnDefinition = "DATE")
    private LocalDate fecha;
    @Column(columnDefinition = "TIME")
    private LocalTime hora_inicio;
    @Column(columnDefinition = "TIME")
    private LocalTime tiempo_consulta;
    
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
    
    @ManyToOne
    @JoinColumn(name = "id_terapeuta")
    private Terapeuta terapeuta;
}
