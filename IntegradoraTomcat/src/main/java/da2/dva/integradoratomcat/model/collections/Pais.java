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
public class Pais {
    @Id
    @Column(name = "siglas", length = 3, nullable = false)
    @CheckColeccion(coleccion = "listapaises" ,groups = DatosPersonales.class)
    private String siglasPais;
    private String nombrePais;
}
