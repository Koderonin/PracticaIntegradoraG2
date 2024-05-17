package da2.dva.integradoratomcat.model.collections;

import da2.dva.integradoratomcat.utils.CheckColeccion;
import da2.dva.integradoratomcat.utils.CheckColeccionLong;
import da2.dva.integradoratomcat.utils.DatosPersonales;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class TipoVia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @CheckColeccionLong(coleccion = "listatiposVia",groups = DatosPersonales.class)
    private Long id;
    private String tipoVia;
}
