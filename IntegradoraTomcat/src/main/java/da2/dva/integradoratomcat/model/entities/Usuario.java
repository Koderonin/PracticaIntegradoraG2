package da2.dva.integradoratomcat.model.entities;

import da2.dva.integradoratomcat.utils.CheckClave;
import da2.dva.integradoratomcat.utils.CheckColeccion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@CheckClave
public abstract class Usuario {

    @NotBlank
    @Pattern(regexp = "^\\w+@([\\w-]+\\.)+[\\w-]{2,4}$")
    //COMPROBAR QUE EXISTE
    private String email;
    @NotBlank
    private String clave;
    @NotBlank
    private String confirmClave;
//????????????????????????????????????
    @NotBlank
    @CheckColeccion(coleccion = "listapreguntas")
    private byte preguntaRecuperacion;
    @NotBlank
    private String respuestaRecuperacion;

    private LocalDate fechaUltimaConexion;

    private LocalDate fechaBloqueo; //Si es null no esta bloqueado

    private Integer numAccesos;

}
