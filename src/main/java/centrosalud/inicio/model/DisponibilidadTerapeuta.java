
package centrosalud.inicio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "disponibilidad")
public class DisponibilidadTerapeuta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_disponibilidad;
    @Enumerated(EnumType.STRING)
    private DayOfWeek dia;
    @Column(columnDefinition = "TIME")
    private LocalTime hora_inicio;
    @Column(columnDefinition = "TIME")
    private LocalTime hora_fin;
    @ManyToOne
    @JoinColumn(name = "id_terapeuta")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Terapeuta terapeuta;
    
}
