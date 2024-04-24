package da2.dva.integradoratomcat.model.entities;

import da2.dva.integradoratomcat.model.embeddables.Direccion;
import da2.dva.integradoratomcat.utils.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Cliente {
    @NotBlank(groups = DatosPersonales.class)
    private String nombre;

    @NotBlank(groups = DatosPersonales.class)
    private String apellidos;

    @NotNull(groups = DatosPersonales.class)
    @CheckColeccion(coleccion = "listageneros")
    private String genero;

    @NotBlank(groups = DatosPersonales.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @CheckMayor18(groups = DatosPersonales.class)
    private LocalDate fechaNacimiento;

    @NotBlank(groups = DatosPersonales.class)
    @CheckColeccion(coleccion = "listapaises")
    private String pais;

    @NotNull(groups = DatosPersonales.class)
    @CheckColeccion(coleccion = "listatiposDocumentos")
    private String tipoDocumento;

    @NotBlank(groups = DatosPersonales.class)
    private String documento;

    @NotBlank(groups = DatosContacto.class)
    @Pattern(regexp = "[0-9]{9}")
    private String telefonoMovil;

    @NotNull(groups = DatosContacto.class)
    private Direccion direccionEntrega;

    @NotBlank(groups = DatosCliente.class)
    private String comentarios;

    @CheckLicencia
    private Boolean aceptacionLicencia;


    private String tipoCliente;

    private BigDecimal gastoAcumuladoCliente;


}
