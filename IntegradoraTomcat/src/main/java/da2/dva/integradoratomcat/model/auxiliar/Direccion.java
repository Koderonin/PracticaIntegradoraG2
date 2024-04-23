package da2.dva.integradoratomcat.model.auxiliar;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_direccion;
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
