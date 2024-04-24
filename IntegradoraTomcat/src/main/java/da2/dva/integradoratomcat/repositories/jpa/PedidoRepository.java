package da2.dva.integradoratomcat.repositories.jpa;

import da2.dva.integradoratomcat.model.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
