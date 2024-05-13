package da2.dva.integradoratomcat.model.composedkeys;

import da2.dva.integradoratomcat.model.entities.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data @AllArgsConstructor @NoArgsConstructor
public class NominaKey implements java.io.Serializable {

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_cliente")
    private Cliente id_cliente;
    private Long num_nomina;
}
