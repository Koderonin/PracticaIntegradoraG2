package da2.dva.integradoratomcat.components;

import da2.dva.integradoratomcat.model.entities.Producto;
import da2.dva.integradoratomcat.repositories.mongo.ProductoRepository;
import da2.dva.integradoratomcat.services.ServicioProducto;
import org.bson.BsonString;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class DatosMongoDB {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ServicioProducto servicioProducto;

    @Bean
    public void insertarCoches() {
        try {
            // pa reiniciar los objetos en la tienda
            servicioProducto.deleteAll();

            productoRepository.save(new Producto("Ferrari", "Testarossa", "FER-00022", "Deportivo Berlinetta biplaza", new BigDecimal("1250000"), 2, new BigDecimal("1200000.5"), 5, true, "", Arrays.asList("http://localhost:8080/img/ferrari_testarossa.jpeg")));
            productoRepository.save(new Producto("Jaguar", "E-Type", "JAG-00017", "Deportivo Berlinetta biplaza", new BigDecimal("98000"), 2, new BigDecimal("1200000.5"), 5, true, "", Arrays.asList("http://localhost:8080/img/jaguar_e_type.jpg")));
            productoRepository.save(new Producto("Mercedes", "Benz Velo", "MER-00002", "Cuatriciclo", new BigDecimal("1680000"), 2, new BigDecimal("1200000.5"), 5, false, "El mejor bólido del mercado", Arrays.asList("http://localhost:8080/img/benz_velo.jpg")));
            productoRepository.save(new Producto("Voisin", "C-25 Aérodyne", "VOI-00003", "Deportivo Berlinetta biplaza", new BigDecimal("267350"), 2, new BigDecimal("1200000.5"), 5, false, "", Arrays.asList("http://localhost:8080/img/voisin_c25.jpg")));
        } catch (Exception e) {
            System.err.println("Omitida carga de datos de Coches: Datos repetidos");
        }

    }
}
