package da2.dva.integradoratomcat.model.embeddables;

import da2.dva.integradoratomcat.utils.CheckColeccion;
//import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

//@Embeddable
@Data
public class Direccion {
    @NotNull
    @CheckColeccion(coleccion = "listavias")
    Long tipo_via;
    @NotBlank
    String nombre_via;
    @NotNull
    //que sea entero
    Integer numero_via;
    //Que sea entero
    String planta;
    String puerta;
    String portal;
    @NotBlank
    String localidad;
    @NotBlank
    //Que sea entero
    String cp;
    String region;
    String pais;

}
