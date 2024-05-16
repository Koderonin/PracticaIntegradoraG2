package da2.dva.integradoratomcat.model.collections;

import da2.dva.integradoratomcat.utils.CheckColeccion;
import da2.dva.integradoratomcat.utils.DatosPersonales;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class TipoDocumento {
    @Id
    @Column(name = "siglas", length = 2, nullable = false)
    @CheckColeccion(coleccion = "listatiposDocumentos", groups = DatosPersonales.class)
    private String siglas;
    private String tipoDocumento;
}
