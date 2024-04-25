package da2.dva.integradoratomcat.model.collections;

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
    private String siglasPais;
    private String nombrePais;
}
