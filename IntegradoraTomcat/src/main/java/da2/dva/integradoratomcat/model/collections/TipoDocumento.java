package da2.dva.integradoratomcat.model.collections;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class TipoDocumento {
    @Id
    private String siglas;
    private String tipoDocumento;
}
