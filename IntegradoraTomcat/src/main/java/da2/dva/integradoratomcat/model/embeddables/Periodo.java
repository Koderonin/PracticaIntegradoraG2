package da2.dva.integradoratomcat.model.embeddables;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public class Periodo {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
