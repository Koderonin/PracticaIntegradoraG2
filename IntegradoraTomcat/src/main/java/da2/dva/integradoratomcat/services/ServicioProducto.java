package da2.dva.integradoratomcat.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import da2.dva.integradoratomcat.model.entities.Producto;
import da2.dva.integradoratomcat.repositories.mongo.ProductoRepository;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.io.DataInput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServicioProducto {

    @Autowired // Esto es para hacer queries a MongoDB directamente, sacando documentos enteros
    MongoTemplate mongoTemplate;

    @Autowired // Esto es para hacer queries a MongoDB, pero sólo para los atributos comunes
    ProductoRepository productoRepository;

    // CREATE
    public void addProducto(Document producto) {
        mongoTemplate.insert(producto, "producto");
    }

    public Producto addProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void addNuevoProducto(Document productoForm) throws JsonProcessingException {

        Document productoDoc = new Document();
// productoDoc.putAll(productoForm);
        String atributo;
        String valor;
        String[] valorArray;
        for (Map.Entry<String, Object> entry : productoForm.entrySet()) {
            atributo = entry.getKey();
            valor = entry.getValue().toString();
            // si es un array
//            if (entry.getValue() instanceof String[]){
//                productoDoc.append(atributo, entry.getValue());
//                continue;
//            }
            // si no es un array, pero debería serlo
            if (valor.contains("/")){
                productoDoc.append(atributo, valor.split("/"));
                continue;
            }
            else
                productoDoc.append(atributo, entry.getValue());
        }

        mongoTemplate.insert(productoDoc, "producto");

        //    Esto es de cuando le venía to' en una String, y se le asignaba a un objeto Producto
        //    ObjectMapper objectMapper = new ObjectMapper();
        //    Producto producto = objectMapper.readValue(productoForm, Producto.class);

    }

    // UPDATE

    // READ
    // to' lo que recupera objetos Document se hace con queries de mongo usando mongoTemplate

    /**
     * Devuelve todos los productos, pero solo los campos comunes
     * @return List<Producto>
     */
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

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

    public Document findById(ObjectId objectId) {
        Document producto = mongoTemplate.findById(objectId, Document.class, "producto");
        return producto;
    }

    public Document findByModelo(BsonString modelo) {
        Query query = new Query();
        query.addCriteria(Criteria.where("modelo").alike((Example<?>) modelo));
        return mongoTemplate.findOne(query, Document.class, "producto");
    }


}
