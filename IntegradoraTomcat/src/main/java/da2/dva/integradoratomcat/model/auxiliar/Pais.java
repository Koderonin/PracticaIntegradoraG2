package da2.dva.integradoratomcat.model.auxiliar;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Pais {
    @Id
    private String siglasPais;
    private String nombrePais;
}
