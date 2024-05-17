package da2.dva.integradoratomcat.model.auxiliar;

import da2.dva.integradoratomcat.model.collections.Genero;
import da2.dva.integradoratomcat.model.collections.TipoVia;
import da2.dva.integradoratomcat.utils.CheckColeccion;
import da2.dva.integradoratomcat.utils.CheckColeccionLong;
import da2.dva.integradoratomcat.utils.DatosContacto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @ManyToOne
    @JoinColumn(name = "tipo_via", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_TIPOVIA_DIRECCION"))
    @Valid
    private TipoVia tipoVia;

    @NotBlank(groups = DatosContacto.class)
    private String nombre_via;

    @NotNull(groups = DatosContacto.class)
    Integer numero_via;
    Integer planta;
    String puerta;
    String portal;

    @NotBlank(groups = DatosContacto.class)
    String localidad;

    @NotBlank (groups = DatosContacto.class)
    String cp;
    String region;

    @NotBlank(groups = DatosContacto.class)
    String pais;

}
