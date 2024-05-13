package da2.dva.integradoratomcat.repositories.mongo;

import da2.dva.integradoratomcat.model.entities.Producto;
import org.bson.BsonString;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductoRepository extends MongoRepository<Producto, String> {

    List<Producto> findAll();

    List<Producto> findById(ObjectId id);

    List<Producto> findByCodigo(String codigo);

    List<Producto> findByMarca(String marca);

    List<Producto> findByValoracionProductoBetween(int min, int max);

    List<Producto> findByEnOferta(boolean oferta);

    List<Producto> findByEsNovedad(boolean novedad);

    List<Producto> findByPrecioBetween(BigDecimal min, BigDecimal max);

}
