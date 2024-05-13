package da2.dva.integradoratomcat.components;

import da2.dva.integradoratomcat.model.entities.Producto;
import da2.dva.integradoratomcat.repositories.mongo.ProductoRepository;
import org.bson.BsonString;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DatosMongoDB {
    @Autowired
    private ProductoRepository productoRepository;

    @Bean
    public void insertarCoches() {
        try {
            productoRepository.save(new Producto("Ferrari", "Testarrossa", "12345", "Deportivo Berlinetta biplaza", new BigDecimal("1200000.5"), 2, new BigDecimal("1200000.5"), 5, true, ""));
            productoRepository.save(new Producto("Jaguar", "E-Type", "12346", "Deportivo Berlinetta biplaza", new BigDecimal("1200000.5"), 2, new BigDecimal("1200000.5"), 5, true, ""));
            productoRepository.save(new Producto("Mercedes", "Benz Velo", "12347", "Cuatriciclo", new BigDecimal("1200000.5"), 2, new BigDecimal("1200000.5"), 5, false, "El mejor vólido del mercado"));
            productoRepository.save(new Producto("Voisin", "C-25 Aérodyne", "12348", "Deportivo Berlinetta biplaza", new BigDecimal("1200000.5"), 2, new BigDecimal("1200000.5"), 5, false, ""));
        } catch (Exception e) {
            System.err.println("Omitida carga de datos de Coches: Datos repetidos");
        }

    }
}
