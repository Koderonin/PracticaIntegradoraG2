package da2.dva.integradoratomcat.model.entities;

import da2.dva.integradoratomcat.model.composedkeys.LineaNominaKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "linea_nomina")
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class LineaNomina {

    @EmbeddedId
    private LineaNominaKey linea_id;

    private BigDecimal importe;

    private String concepto;
}
