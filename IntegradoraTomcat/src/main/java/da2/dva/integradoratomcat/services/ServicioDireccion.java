package da2.dva.integradoratomcat.services;

import da2.dva.integradoratomcat.model.auxiliar.Direccion;
import da2.dva.integradoratomcat.repositories.jpa.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioDireccion {

    @Autowired
    private DireccionRepository direccionRepository;


    // CREATE

    public Direccion crearNuevaDireccion(Direccion direccion) {
        return direccionRepository.save(direccion);
    }

    // READ
    public List<Direccion> findAll() {
        return direccionRepository.findAll();
    }

    // UPDATE

    public void save(Direccion direccion) {
        direccionRepository.save(direccion);
    }

    // DELETE
    public void borrarTodasDirecciones() {
        direccionRepository.deleteAll();
    }
}
