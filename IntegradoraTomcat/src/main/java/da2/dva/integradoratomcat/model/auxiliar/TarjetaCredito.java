package da2.dva.integradoratomcat.model.auxiliar;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class TarjetaCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tarjeta;
    private String tipoTarjetaCredito;
    private Integer cvv;
    private Long numeroTarjeta;
    private LocalDate fechaVencimiento;
    private Boolean predeterminada;

}
