package da2.dva.integradoratomcat.model.composedkeys;

import da2.dva.integradoratomcat.model.entities.Nomina;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

@Embeddable
public class LineaNominaKey implements java.io.Serializable {

    private NominaKey nomina_id;
    private Long linea_id;
}
