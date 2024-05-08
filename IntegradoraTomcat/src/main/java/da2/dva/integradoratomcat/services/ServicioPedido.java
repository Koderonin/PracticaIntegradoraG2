package da2.dva.integradoratomcat.services;

import da2.dva.integradoratomcat.model.entities.Pedido;
import da2.dva.integradoratomcat.repositories.jpa.PedidoRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioPedido {

    @Autowired
    PedidoRepository pedidoRepository;

    public void crearPedido(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    public void eliminarPedido(Pedido pedido) {
        pedidoRepository.delete(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public void borrarTodosPedidos() {
        pedidoRepository.deleteAll();
    }

    public List<Pedido> buscarPedidoPorProductoId(String productoId) {
        return pedidoRepository.findPedidoByProductoId(productoId);
    }

}
