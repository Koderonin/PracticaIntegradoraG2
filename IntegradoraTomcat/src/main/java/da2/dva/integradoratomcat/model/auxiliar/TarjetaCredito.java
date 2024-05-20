package da2.dva.integradoratomcat.model.auxiliar;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class TarjetaCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tarjeta;
    @NotNull
    private String tipoTarjetaCredito;
    @Pattern(regexp = "[0-9]{3}")
    private String cvv;
    @NotNull
    private Long numeroTarjeta;
    @NotNull
    private LocalDate fechaVencimiento;
    private Boolean predeterminada;
}
