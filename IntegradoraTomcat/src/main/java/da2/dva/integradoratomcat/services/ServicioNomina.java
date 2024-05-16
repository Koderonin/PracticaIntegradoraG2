package da2.dva.integradoratomcat.services;

import da2.dva.integradoratomcat.model.composedkeys.NominaKey;
import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.LineaNomina;
import da2.dva.integradoratomcat.model.entities.Nomina;
import da2.dva.integradoratomcat.repositories.jpa.LineaNominaRepository;
import da2.dva.integradoratomcat.repositories.jpa.NominaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ServicioNomina {

    @Autowired
    private NominaRepository nominaRepository;

    @Autowired
    private LineaNominaRepository lineaNominaRepository;

    // READ

    public Nomina getUltimaNomina(Cliente cliente) {
        return nominaRepository.findTopByCliente(cliente);
    }

    public List<Nomina> findAll() {
        return nominaRepository.findAll();
    }



    // CREATE

    @Transactional
    public Nomina crearNuevaNomina(Cliente cliente) {
        Nomina ultimaNomina = nominaRepository.findTopByCliente(cliente);

        Nomina nuevaNomina = new Nomina();
        nuevaNomina.setId_nomina(new NominaKey(cliente, ultimaNomina == null ? 1L : ultimaNomina.getId_nomina().getNum_nomina() + 1L));
        nuevaNomina.setSalario(new BigDecimal(0));
        return nominaRepository.save(nuevaNomina);
    }

    // UPDATE

    public void save(Nomina nomina) {
        nominaRepository.save(nomina);
    }

//    @Transactional
//    public void setSalario(Nomina nomina){
//
//        // recogemos los importes de cada apunte relacionado con la nómina y sumarlos
//        List<LineaNomina> lineas = lineaNominaRepository.findByNomina(nomina.getId_nomina());
//
//        // reiniciamos el importe de la nómina
//        nomina.setSalario(new BigDecimal(0));
//
//        for (LineaNomina linea : lineas) {
//            nomina.setSalario(nomina.getSalario().add(linea.getImporte()));
//        }
//
//        // actualizamos la nómina
//        nominaRepository.save(nomina);
//    }

    // DELETE

    public void borrarNominasCliente(Cliente cliente) {
        List<Nomina> nominas = nominaRepository.findByCliente(cliente);
        for (Nomina nomina : nominas) {
            borrarNomina(nomina);
        }
    }

    public void borrarNomina(Nomina nomina) {
        nominaRepository.delete(nomina);
    }

    public void borrarTodasNominas() {
        nominaRepository.deleteAll();
    }
}
