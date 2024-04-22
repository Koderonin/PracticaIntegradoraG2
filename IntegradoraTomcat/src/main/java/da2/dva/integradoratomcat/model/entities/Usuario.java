package da2.dva.integradoratomcat.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private String email;
    private String clave;
    private LocalDate fechaUltimaConexion;
    private Integer numAccesos;
    private LocalDate fechaBloqueo; //Si es null no esta bloqueado
    private byte preguntaRecuperacion; //TODO: Implementar preguntas
    private String respuestaRecuperacion;
    //private Auditoria //TODO: Implementar auditoria



}
