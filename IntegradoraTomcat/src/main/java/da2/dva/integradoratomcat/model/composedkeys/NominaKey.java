package da2.dva.integradoratomcat.model.composedkeys;

import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@Data @AllArgsConstructor @NoArgsConstructor
public class NominaKey implements java.io.Serializable {

    @ManyToOne
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "FK_CLIENTE_NOMINA_CLIENTE_ID_CLIENTE"))
    private Cliente cliente_id;
    private Long num_nomina;
}
