package da2.dva.integradoratomcat.utils;


import da2.dva.integradoratomcat.services.ServicioColecciones;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Objects;

public class ValidadorColeccionLong implements ConstraintValidator<CheckColeccionLong, Long> {
    private Map<Long, String> coleccion;
    @Autowired
    private ServicioColecciones servicioColecciones;

    @Override
    public void initialize(CheckColeccionLong constraintAnnotation) {
        this.servicioColecciones = ApplicationContextProvider.getApplicationContext().getBean(ServicioColecciones.class);

        switch (constraintAnnotation.coleccion()) {

            case "listapreguntas":
                servicioColecciones.cargarPreguntas();
                this.coleccion = servicioColecciones.getPreguntas();
                break;

            case "listatiposVia":
                this.coleccion = servicioColecciones.getTiposVia();
                break;


        }
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return (this.coleccion.containsKey(value) || Objects.isNull(value));
    }
}
