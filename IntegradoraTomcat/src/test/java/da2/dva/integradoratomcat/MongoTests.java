package da2.dva.integradoratomcat;

import da2.dva.integradoratomcat.services.ServicioProducto;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MongoTests {

    @Autowired
    private ServicioProducto servicioProducto;

    @Test
    public void getProducto() {
        Document producto = servicioProducto.findById(new ObjectId("66412285b359de1aa522ed20"));
        assertEquals(new ObjectId("66412285b359de1aa522ed20"), producto.get("_id"));
    }
}
