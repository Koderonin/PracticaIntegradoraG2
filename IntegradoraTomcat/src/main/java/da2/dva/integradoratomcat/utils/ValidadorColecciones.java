package da2.dva.integradoratomcat.utils;

import da2.dva.integradoratomcat.services.ServicioColecciones;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Objects;

public class ValidadorColecciones implements ConstraintValidator<CheckColeccion, String>{
    private Map<String, String> coleccion;
    @Autowired
    private ServicioColecciones servicioColecciones;
    @Override
    public void initialize(CheckColeccion constraintAnnotation) {

        switch (constraintAnnotation.coleccion()) {
            case "listapaises":
                this.coleccion = (Map<String, String>) servicioColecciones.devuelvePaises();
                break;
            case "listageneros":
                this.coleccion = (Map<String, String>) servicioColecciones.devuelveGeneros();
                break;
            case "listatiposDocumentos":
                this.coleccion = (Map<String, String>) servicioColecciones.devuelveTiposDocumentos();
                break;
            case "listapreguntas":
                this.coleccion = (Map<String, String>) servicioColecciones.devuelvePreguntas();
                break;
            case "listatiposVia":
                this.coleccion = (Map<String, String>) servicioColecciones.devuelveTiposVia();
                break;

                /* TODO: VER COMO VALIDAR USUARIO REPETIDO
            case "usuarios":
                this.coleccion = (Map<String, String>) servicioColecciones.devuelveUsuarios();
                break;

                 */
        }
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return (Objects.nonNull(value) && this.coleccion.containsKey(value)) ;
    }
}
