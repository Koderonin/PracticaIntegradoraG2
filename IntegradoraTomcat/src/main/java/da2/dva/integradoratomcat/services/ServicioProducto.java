package da2.dva.integradoratomcat.services;

import da2.dva.integradoratomcat.model.entities.Producto;
import da2.dva.integradoratomcat.repositories.mongo.ProductoRepository;
import org.bson.BsonString;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioProducto {

    @Autowired // Esto es para hacer queries a MongoDB directamente, sacando documentos enteros
    MongoTemplate mongoTemplate;

    @Autowired // Esto es para hacer queries a MongoDB, pero sólo para los atributos comunes
    ProductoRepository productoRepository;

    // TODO : Implementar Servicio de Productos

    /**
     * Devuelve todos los productos, pero solo los campos comunes
     * @return List<Producto>
     */
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    // to' esto es de recuperar documentos, así que se hace con queries de mongo usando mongoTemplate

    /**
     * Devuelve todos los productos, con todos sus campos.
     * @return List<Document>
     */
    public List<Document> findAllDocuments() {
        return mongoTemplate.findAll(Document.class, "producto");
    }

    public List<Document> findByAttribute(String atributo, String value) {
        Query query = new Query();
        query.addCriteria(Criteria.where(atributo).is(value));
        return mongoTemplate.find(query, Document.class, "producto");
    }

    public Document findByCodigo(BsonString codigo) {
        return mongoTemplate.findById(codigo, Document.class, "producto");
    }

    // añadir y persistir en la base de datos un producto
    // hay que crear una lógica (aquí?) para hacerlo

    public void addProducto(Document producto) {
        mongoTemplate.insert(producto, "producto");
    }
}
