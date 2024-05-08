package da2.dva.integradoratomcat.repositories.jpa;

import da2.dva.integradoratomcat.model.composedkeys.LineaPedidoKey;
import da2.dva.integradoratomcat.model.entities.LineaPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaPedidoRepository extends JpaRepository<LineaPedido, LineaPedidoKey> {
    // No se me ocurre en esto cómo usar na
}
