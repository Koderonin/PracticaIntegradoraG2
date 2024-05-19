package da2.dva.integradoratomcat.model.entities;

import da2.dva.integradoratomcat.utils.CheckColeccionLong;
import jakarta.persistence.*;
import da2.dva.integradoratomcat.utils.CheckClave;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Data @AllArgsConstructor @NoArgsConstructor
@CheckClave
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_usuario;

    @NotBlank
    @Pattern(regexp = "^\\w+@([\\w-]+\\.)+[\\w-]{2,4}$")
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

    private LocalDateTime fechaUltimaConexion;
    private Integer numAccesos;
    private LocalDateTime fechaBloqueo; //Si es null no esta bloqueado
    private Boolean administrador;

    public Usuario(String email, String clave,  Long preguntaRecuperacion, String respuestaRecuperacion, Boolean administrador) {
        setEmail(email);
        setClave(clave);
        setConfirmClave(clave);
        setPreguntaRecuperacion(preguntaRecuperacion);
        setRespuestaRecuperacion(respuestaRecuperacion);
        setNumAccesos(0);
        setAdministrador(administrador);
    }

    //private Auditoria //TODO: Implementar auditoria



}
