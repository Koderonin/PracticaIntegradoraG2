package da2.dva.integradoratomcat.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
//persistimos en mySQL


public class Carrito {

    private BigDecimal precio;

}
