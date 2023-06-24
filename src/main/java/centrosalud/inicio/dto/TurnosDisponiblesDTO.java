
package centrosalud.inicio.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TurnosDisponiblesDTO implements Serializable{
    
    private LocalDate fecha;
    private String dia;
    private LocalTime hora_inicio;
    private LocalTime hora_fin;
    private List<LocalTime> cantidadTurnos = new ArrayList<>();
}


