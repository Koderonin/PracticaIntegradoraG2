package da2.dva.integradoratomcat.model.entities;

import da2.dva.integradoratomcat.model.embeddables.Direccion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    private String genero;
    private LocalDate fechaNacimiento;
    private String pais; //TODO: PREGUNTAR A JOSERRA SI ES EMBEDDABLE, O SI ES STRING
    private String tipoDocumento;
    private String documento;
    private String telefonoMovil;
    private String nombre;
    private String apellidos;
    private BigDecimal gastoAcumuladoCliente;
    private Direccion direccionEntrega;
    private String tipoCliente;
    private String comentarios;
    private Boolean aceptacionLicenicia;




}
