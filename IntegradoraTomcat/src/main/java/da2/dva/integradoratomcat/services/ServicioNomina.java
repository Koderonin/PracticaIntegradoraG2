package da2.dva.integradoratomcat.services;

import da2.dva.integradoratomcat.model.composedkeys.NominaKey;
import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.Nomina;
import da2.dva.integradoratomcat.repositories.jpa.NominaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioNomina {

    @Autowired
    private NominaRepository nominaRepository;

    @Transactional
    public Nomina crearNuevaNomina(Cliente cliente) {
        Nomina ultimaNomina = nominaRepository.findTopByCliente(cliente);

        Nomina nuevaNomina = new Nomina();
        nuevaNomina.setId_nomina(new NominaKey(cliente, ultimaNomina == null ? 1L : ultimaNomina.getId_nomina().getNum_nomina() + 1L));

        return nominaRepository.save(nuevaNomina);
    }
}
