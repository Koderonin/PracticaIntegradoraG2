package da2.dva.integradoratomcat.utils;

import da2.dva.integradoratomcat.services.ServicioColecciones;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Objects;

public class ValidadorColecciones implements ConstraintValidator<CheckColeccion, String>{
    private Map<String, String> coleccion;
    @Autowired
    private ServicioColecciones servicioColecciones;
    @Override
    public void initialize(CheckColeccion constraintAnnotation) {
        this.servicioColecciones = ApplicationContextProvider.getApplicationContext().getBean(ServicioColecciones.class);

        switch (constraintAnnotation.coleccion()) {

            case "listapaises":
                this.coleccion = servicioColecciones.getPaises();
                break;

            case "listageneros":
                this.coleccion = servicioColecciones.getGeneros();
                break;
            case "listatiposDocumentos":
                this.coleccion = servicioColecciones.getTiposDocumentos();
                break;
        }
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return (this.coleccion.containsKey(value) || Objects.isNull(value));
    }
}
