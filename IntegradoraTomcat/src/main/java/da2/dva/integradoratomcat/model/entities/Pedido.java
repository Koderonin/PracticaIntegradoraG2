package da2.dva.integradoratomcat.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private LocalDate fechaRealizacionPedido;
    private BigDecimal precioTotal;
    private String estado;

}

