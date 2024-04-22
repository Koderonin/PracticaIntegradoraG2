package da2.dva.integradoratomcat.model.embeddables;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Direccion {
    String tipo_via;
    String nombre_via;
    Integer numero_via;
    String piso;
    String puerta;
    String localidad;
    Integer cp;
    String region;
    String pais;
}
