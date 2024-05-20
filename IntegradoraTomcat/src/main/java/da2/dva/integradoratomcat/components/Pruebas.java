//TODO: convertir en archivo de carga de datos
package da2.dva.integradoratomcat.components;

import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.LineaNomina;
import da2.dva.integradoratomcat.model.entities.Nomina;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.repositories.jpa.ClienteRepository;
import da2.dva.integradoratomcat.repositories.jpa.UsuarioClienteRepository;
import da2.dva.integradoratomcat.services.ServicioCliente;
import da2.dva.integradoratomcat.services.ServicioLineaNomina;
import da2.dva.integradoratomcat.services.ServicioNomina;
import da2.dva.integradoratomcat.services.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Pruebas {

    @Autowired
    ServicioCliente servicioCliente;

    @Autowired
    ServicioUsuario servicioUsuario;

    @Autowired
    ServicioNomina servicioNomina;

    @Autowired
    ServicioLineaNomina servicioLineaNomina;

    @Bean
    public void prTest() throws InterruptedException {

        servicioLineaNomina.borrarTodasLineasNomina();
        servicioNomina.borrarTodasNominas();

        Cliente c = servicioCliente.getClienteByEmail("admin@integradora.jpa");
        Nomina n = new Nomina();
        n.setAnio(2022L);
        n.setMes(2L);

        servicioNomina.crearNuevaNomina(c, n);

        LineaNomina ln1 = new LineaNomina();

        ln1.setConcepto("Sueldo");
        ln1.setImporte(new BigDecimal(1080));
        servicioLineaNomina.nuevaLineaNomina(ln1, n);

        LineaNomina ln2 = new LineaNomina();

        ln2.setConcepto("Bonus");
        ln2.setImporte(new BigDecimal(230));
        servicioLineaNomina.nuevaLineaNomina(ln2, n);

        LineaNomina ln5 = new LineaNomina();

        ln5.setConcepto("Retención");
        ln5.setImporte(new BigDecimal(-180));
        servicioLineaNomina.nuevaLineaNomina(ln5, n);

        Nomina n2 = new Nomina();
        n2.setAnio(2022L);
        n2.setMes(3L);

        servicioNomina.crearNuevaNomina(c, n2);

        LineaNomina ln3 = new LineaNomina();

        ln3.setConcepto("Sueldo");
        ln3.setImporte(new BigDecimal(1080));
        servicioLineaNomina.nuevaLineaNomina(ln3, n2);

        LineaNomina ln4 = new LineaNomina();

        ln4.setConcepto("Bonus");
        ln4.setImporte(new BigDecimal(275));
        servicioLineaNomina.nuevaLineaNomina(ln4, n2);

        LineaNomina ln6 = new LineaNomina();

        ln6.setConcepto("Retención");
        ln6.setImporte(new BigDecimal(-189));
        servicioLineaNomina.nuevaLineaNomina(ln6, n2);

    }
}