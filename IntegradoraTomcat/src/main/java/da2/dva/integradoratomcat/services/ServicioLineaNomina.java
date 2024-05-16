package da2.dva.integradoratomcat.services;

import da2.dva.integradoratomcat.model.composedkeys.LineaNominaKey;
import da2.dva.integradoratomcat.model.composedkeys.NominaKey;
import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.LineaNomina;
import da2.dva.integradoratomcat.model.entities.Nomina;
import da2.dva.integradoratomcat.repositories.jpa.LineaNominaRepository;
import da2.dva.integradoratomcat.repositories.jpa.NominaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioLineaNomina {

    @Autowired
    private NominaRepository nominaRepository;

    @Autowired
    private LineaNominaRepository lineaNominaRepository;

    // CREATE

//    public LineaNomina nuevaLineaNomina(Nomina nomina) {
//        LineaNomina ultimaLineaNomina = lineaNominaRepository.findTopByNomina(nomina.getId_nomina());
//
//        LineaNomina nuevaLineaNomina = new LineaNomina();
//        nuevaLineaNomina.setLinea_id(new LineaNominaKey(nomina, ultimaLineaNomina == null ? 1L : ultimaLineaNomina.getLinea_id().getLinea_id() + 1L));
//
//        return nuevaLineaNomina;
//    }

    // READ

//    public List<LineaNomina> getLineasNomina(Nomina nomina) {
//        NominaKey nominaKey = nomina.getId_nomina();
//        return lineaNominaRepository.findByNomina(nominaKey);
//    }

    // UPDATE

    public void save(LineaNomina lineaNomina) {
        lineaNominaRepository.save(lineaNomina);
    }

    // DELETE

//    public void borrarLineasPorNomina(Nomina nomina) {
//        lineaNominaRepository.deleteByNomina(nomina.getId_nomina());
//    }
    public void borrarTodasLineasNomina() {
        lineaNominaRepository.deleteAll();
    }
}
