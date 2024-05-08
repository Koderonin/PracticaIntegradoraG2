package da2.dva.integradoratomcat.repositories.jpa;

import da2.dva.integradoratomcat.model.auxiliar.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {

    List<Direccion> findByLocalidad(String localidad);

    List<Direccion> findByPais(String pais);

    List<Direccion> findByCp(String cp);
}
