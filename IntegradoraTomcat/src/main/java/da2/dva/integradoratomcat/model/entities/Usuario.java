package da2.dva.integradoratomcat.model.entities;

import da2.dva.integradoratomcat.utils.CheckClave;
import da2.dva.integradoratomcat.utils.CheckColeccion;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@CheckClave
public  class Usuario {

    @NotBlank
    @Pattern(regexp = "^\\w+@([\\w-]+\\.)+[\\w-]{2,4}$")
    //COMPROBAR QUE EXISTE
    private String email;
    @NotBlank
    @Size(min = 6, max = 12, message = "La contraseña debe tener entre 6 y 12 caracteres")
    @Pattern.List({
            @Pattern(regexp = ".*[a-z].*", message = "La contraseña debe contener al menos una letra minúscula"),
            @Pattern(regexp = ".*[A-Z].*", message = "La contraseña debe contener al menos una letra mayúscula"),
            @Pattern(regexp = ".*\\d.*", message = "La contraseña debe contener al menos un dígito"),
            @Pattern(regexp = ".*[!#$%&].*", message = "La contraseña debe contener al menos un caracter especial: !, #, $, %, &")
    })
    private String clave;
    @NotBlank
    private String confirmClave;
//????????????????????????????????????
    @NotNull
    @Size(min = 1)
    @CheckColeccion(coleccion = "listapreguntas")
    private String preguntaRecuperacion;
    @NotBlank
    private String respuestaRecuperacion;

    private LocalDate fechaUltimaConexion;

    private LocalDate fechaBloqueo; //Si es null no esta bloqueado

    private Integer numAccesos;

    //TODO: IMPLEMENTAR BLOQUEOS

}
