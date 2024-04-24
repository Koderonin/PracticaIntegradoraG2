package da2.dva.integradoratomcat.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@MappedSuperclass
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_usuario;
    private String email;
    private String clave;
    @Transient
    private String confirmClave;
    private LocalDate fechaUltimaConexion;
    private Integer numAccesos;
    private LocalDate fechaBloqueo; //Si es null no esta bloqueado
    private String preguntaRecuperacion; //TODO: Implementar preguntas
    private String respuestaRecuperacion;

}
