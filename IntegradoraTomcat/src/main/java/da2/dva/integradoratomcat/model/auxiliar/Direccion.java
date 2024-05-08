package da2.dva.integradoratomcat.model.auxiliar;

import da2.dva.integradoratomcat.utils.CheckColeccion;
import da2.dva.integradoratomcat.utils.CheckColeccionLong;
import da2.dva.integradoratomcat.utils.DatosContacto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_direccion;

    @NotNull(groups = DatosContacto.class)
    @CheckColeccionLong(coleccion = "listatiposVia",  groups = DatosContacto.class)
    Long tipo_via;
    @NotBlank(groups = DatosContacto.class)
    String nombre_via;
    @NotNull(groups = DatosContacto.class)
    Integer numero_via;
    String planta;
    String puerta;
    String portal;
    @NotBlank(groups = DatosContacto.class)
    String localidad;
    @NotBlank (groups = DatosContacto.class)
    String cp;
    String region;
    String pais;

}
