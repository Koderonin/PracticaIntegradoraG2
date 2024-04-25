package da2.dva.integradoratomcat.model.auxiliar;

import da2.dva.integradoratomcat.utils.CheckColeccion;
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

    @NotNull
    //@CheckColeccion(coleccion = "listavias")
    Long tipo_via;
    @NotBlank
    String nombre_via;
    @NotNull
    Integer numero_via;
    String planta;
    String puerta;
    String portal;
    @NotBlank
    String localidad;
    @NotBlank
    String cp;
    String region;
    String pais;

}
