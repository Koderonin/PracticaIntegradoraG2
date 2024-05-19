package da2.dva.integradoratomcat.model.composedkeys;

import da2.dva.integradoratomcat.model.entities.Carrito;
import da2.dva.integradoratomcat.model.entities.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class LineaCarritoId implements java.io.Serializable {

    private Carrito carrito;
    private Producto producto_id;

}
