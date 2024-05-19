package da2.dva.integradoratomcat.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedido_id;
    private LocalDate fechaRealizacionPedido;
    private BigDecimal precioTotal;
    private String estado;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", foreignKey = @ForeignKey(name = "FK_CLIENTE_PEDIDO"))
    private Cliente cliente;

    @OneToMany
    @JoinColumn(name = "pedido_id", foreignKey = @ForeignKey(name = "FK_PEDIDO_LINEA_PEDIDO_ID_PEDIDO"))
    private Set<LineaPedido> lineasPedido = new HashSet<>();

}

