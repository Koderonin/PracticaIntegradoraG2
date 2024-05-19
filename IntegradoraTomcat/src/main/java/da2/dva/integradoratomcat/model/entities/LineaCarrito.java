package da2.dva.integradoratomcat.model.entities;

import da2.dva.integradoratomcat.model.composedkeys.LineaCarritoId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(LineaCarritoId.class)
public class LineaCarrito {

    @Id
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
   // @JoinColumn(name = "cliente", foreignKey = @ForeignKey(name = "FK_CARRO_LINEA_CARRO_ID_CARRO"))
    private Carrito carrito;

    @Id
    private String producto_id;

    private byte cantidad;
}
