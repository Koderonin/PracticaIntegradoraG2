package da2.dva.integradoratomcat.model.composedkeys;

import da2.dva.integradoratomcat.model.entities.Pedido;
import da2.dva.integradoratomcat.model.entities.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class LineaPedidoKey implements java.io.Serializable {
    private Producto producto;
    private Pedido pedido;
}
