package da2.dva.integradoratomcat.model.entities;

import da2.dva.integradoratomcat.utils.CheckColeccionLong;
import jakarta.persistence.*;
import da2.dva.integradoratomcat.utils.CheckClave;
import da2.dva.integradoratomcat.utils.CheckColeccion;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@CheckClave
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_usuario;

    @NotBlank
    @Pattern(regexp = "^\\w+@([\\w-]+\\.)+[\\w-]{2,4}$")
    //COMPROBAR QUE EXISTE
    private String email;
    @NotBlank
    @Size(min = 6, max = 12, message = "{password.invalidSize}")
    @Pattern.List({
            @Pattern(regexp = ".*[a-z].*", message="{password.invalidPattern.minuscula}"),
            @Pattern(regexp = ".*[A-Z].*", message = "{password.invalidPattern.mayuscula}"),
            @Pattern(regexp = ".*\\d.*", message = "{password.invalidPattern.numero}"),
            @Pattern(regexp = ".*[!#$%&].*", message = "{password.invalidPattern.simbolo}")
    })
    private String clave;
    @Transient
    @NotBlank
    private String confirmClave;
    @NotNull
    @CheckColeccionLong(coleccion = "listapreguntas")
    private Long preguntaRecuperacion;
    @NotBlank
    private String respuestaRecuperacion;

    private LocalDate fechaUltimaConexion;
    private Integer numAccesos;
    private LocalDate fechaBloqueo; //Si es null no esta bloqueado

    public Usuario(UUID idUsuario, String email, String clave, String confirmClave, Long preguntaRecuperacion, String respuestaRecuperacion) {
        this.id_usuario = idUsuario;
        this.email = email;
        this.clave = clave;
        this.confirmClave = confirmClave;
        this.preguntaRecuperacion = preguntaRecuperacion;
        this.respuestaRecuperacion = respuestaRecuperacion;
        this.numAccesos = 0;
    }

    public Usuario(String email, String clave,  Long preguntaRecuperacion, String respuestaRecuperacion) {
        setEmail(email);
        setClave(clave);
        setConfirmClave(clave);
        setPreguntaRecuperacion(preguntaRecuperacion);
        setRespuestaRecuperacion(respuestaRecuperacion);
        setNumAccesos(0);
    }

    //private Auditoria //TODO: Implementar auditoria



}
