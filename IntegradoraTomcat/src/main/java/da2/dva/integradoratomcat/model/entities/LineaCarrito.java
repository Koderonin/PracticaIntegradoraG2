package da2.dva.integradoratomcat.model.entities;

import da2.dva.integradoratomcat.model.composedkeys.LineaCarritoId;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(LineaCarritoId.class)
public class LineaCarrito {

    @Id
    private Carrito id_carrito;
    @Id
    private Producto id_producto;

    private byte cantidad;
}
