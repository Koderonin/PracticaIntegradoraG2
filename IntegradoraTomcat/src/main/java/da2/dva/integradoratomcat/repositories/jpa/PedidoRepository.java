package da2.dva.integradoratomcat.repositories.jpa;

import da2.dva.integradoratomcat.model.entities.Pedido;
import da2.dva.integradoratomcat.repositories.mongo.ProductoRepository;
import da2.dva.integradoratomcat.services.ServicioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    /**
     * Lista pedidos que contengan cierto producto, haciendo un JOIN con linea pedido, que es el que contiene la id del producto
     * Allá donde llame a este método, habrá que encontrar el id del producto con métodos de ServicioProducto y pasárselo.
     *
     * @param productoId
     * @return List<Pedido>
     */
    @Query("select p from Pedido p join p.lineasPedido lp where lp.producto_id = ?1")
    List<Pedido> findPedidoByProductoId(String productoId);


}
