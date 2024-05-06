package da2.dva.integradoratomcat.model.composedkeys;

import da2.dva.integradoratomcat.model.entities.Pedido;
import da2.dva.integradoratomcat.model.entities.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.UUID;

@Data @AllArgsConstructor @NoArgsConstructor
public class LineaPedidoKey implements java.io.Serializable {
    private String producto_id;
    private Pedido pedido;
}
