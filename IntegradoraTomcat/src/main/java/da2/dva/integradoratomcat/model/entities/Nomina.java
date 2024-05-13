package da2.dva.integradoratomcat.model.entities;

import da2.dva.integradoratomcat.model.composedkeys.NominaKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Table(name = "nomina")
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Nomina {

    @EmbeddedId
    private NominaKey id_nomina;

    private Long mes;
    private Long anio;

    private BigDecimal salario;
}
