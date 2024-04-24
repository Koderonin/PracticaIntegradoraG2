package da2.dva.integradoratomcat.model.auxiliar;

import jakarta.persistence.*;
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
    Long tipo_via;
    String nombre_via;
    Integer numero_via;
    String planta;
    String puerta;
    String localidad;
    Integer cp;
    String region;
    String pais;

}
