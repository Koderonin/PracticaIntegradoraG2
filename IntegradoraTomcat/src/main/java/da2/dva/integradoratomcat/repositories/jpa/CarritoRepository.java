package da2.dva.integradoratomcat.repositories.jpa;

import da2.dva.integradoratomcat.model.entities.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {


}
