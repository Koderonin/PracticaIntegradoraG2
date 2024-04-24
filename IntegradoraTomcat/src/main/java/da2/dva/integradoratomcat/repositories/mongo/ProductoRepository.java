package da2.dva.integradoratomcat.repositories.mongo;

import da2.dva.integradoratomcat.model.entities.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductoRepository extends MongoRepository<Producto, Long> {

}
