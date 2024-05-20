package da2.dva.integradoratomcat.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import da2.dva.integradoratomcat.model.entities.Producto;
import da2.dva.integradoratomcat.repositories.mongo.ProductoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
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

    public void addNuevoProducto(Document productoForm) {

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
        List<Document> listado = mongoTemplate.findAll(Document.class, "producto");
        for (Document doc : listado) {
            ObjectId id = (ObjectId) doc.get("_id");
            String idStr = id.toHexString();
            doc.replace("_id", idStr);
        }
        return listado;
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

    public Document findByCodigo(String codigo) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("codigo").is(codigo));
        List<Document> list = mongoTemplate.find(query, Document.class, "producto");
        return list.get(0);
    }

    public Document findByModelo(BsonString modelo) {
        Query query = new Query();
        query.addCriteria(Criteria.where("modelo").alike((Example<?>) modelo));
        return mongoTemplate.findOne(query, Document.class, "producto");
    }

    // UPDATE

    public void updateProduct(Document product) {
        ObjectId id = new ObjectId(product.get("_id").toString());
        product.replace("_id", id.toHexString());
        mongoTemplate.save(product, "producto");
    }

    // REMOVE

    public void deleteAll() {
        mongoTemplate.dropCollection("producto");
    }

}
