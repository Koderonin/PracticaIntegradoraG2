package da2.dva.integradoratomcat.model.entities;

import da2.dva.integradoratomcat.model.composedkeys.LineaPedidoKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@Table(name = "linea_pedido")
@Entity
@IdClass(LineaPedidoKey.class)
@AllArgsConstructor @NoArgsConstructor @Data
public class LineaPedido {
    @Id
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", foreignKey = @ForeignKey(name = "FK_PEDIDO_LINEA_PEDIDO_ID_PEDIDO"))
    private Pedido pedido;

    @Id
    private ObjectId producto_id;

    private Integer unidades;

    private BigDecimal precioUnitario;

    public void setPrecioUnitario(Producto producto) {
        this.precioUnitario = producto.getPrecio();
    }


}

