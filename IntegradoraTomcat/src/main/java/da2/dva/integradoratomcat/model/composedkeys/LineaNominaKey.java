package da2.dva.integradoratomcat.model.composedkeys;

import da2.dva.integradoratomcat.model.entities.Nomina;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineaNominaKey implements java.io.Serializable {

    @ManyToOne(cascade = CascadeType.REMOVE)
    @AttributeOverrides({
            @AttributeOverride(name = "id_cliente", column = @Column(name = "id_cliente")),
            @AttributeOverride(name = "num_nomina", column = @Column(name = "num_nomina"))
    })
    private Nomina nomina_id;
    private Long linea_id;
}
