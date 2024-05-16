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

            productoRepository.save(new Producto("Nike", "Pegasus 40", "FER-00022", "Zapatillas running de mujer", new BigDecimal("120.99"), 2, new BigDecimal("0"), 5, true, "", Arrays.asList("http://localhost:8080/img/zapatillas/zapatilla1.avif")));
            productoRepository.save(new Producto("Nike", "Juniper Trail 2", "JAG-00017", "Zapatillas de trail running de hombre", new BigDecimal("99.99"), 2, new BigDecimal("0"), 5, true, "", Arrays.asList("http://localhost:8080/img/zapatillas/zapatilla2.avif")));
            productoRepository.save(new Producto("ASICS", " Jolt 4", "MER-00002", "Zapatillas de running de niños", new BigDecimal("60.00"), 2, new BigDecimal("0"), 5, true, "Las mejores zapatillas que he probado", Arrays.asList("http://localhost:8080/img/zapatillas/zapatilla3.avif")));
            productoRepository.save(new Producto("Brooks", " Caldera 7 ", "VOI-00005", "Zapatillas de running de hombre", new BigDecimal("104.99"), 2, new BigDecimal("0"), 5, false, "", Arrays.asList("http://localhost:8080/img/zapatillas/zapatilla4.avif")));
            productoRepository.save(new Producto("Mountain PRO", " Low Mountain PRO ", "VOI-00004", "Zapatillas de montaña de niños Titan", new BigDecimal("90.99"), 2, new BigDecimal("0"), 5, false, "", Arrays.asList("http://localhost:8080/img/zapatillas/zapatilla5.avif")));
            productoRepository.save(new Producto("Nike", "COURT VISION ", "VOI-00006", "Nike Sportswear", new BigDecimal("110.00"), 2, new BigDecimal("0"), 5, false, "", Arrays.asList("http://localhost:8080/img/zapatillas/zapatilla6.avif")));
            productoRepository.save(new Producto("Nike", " Estefanía", "VOI-00007", "Zapatillas de running de muje", new BigDecimal("47.99"), 2, new BigDecimal("0"), 5, false, "", Arrays.asList("http://localhost:8080/img/zapatillas/zapatilla7.avif")));
            productoRepository.save(new Producto("Under Armour", " Tori Rock BSR 4", "VOI-00008", "Zapatillas pa' machacas", new BigDecimal("69.99"), 2, new BigDecimal("0"), 5, false, "", Arrays.asList("http://localhost:8080/img/zapatillas/zapatilla8.avif")));
            productoRepository.save(new Producto("Converse", "Chuck Taylor All Star Move Platform", "VOI-00009", "Para parecerte a Taylor Swift, guapa", new BigDecimal("70.99"), 2, new BigDecimal("0"), 5, false, "", Arrays.asList("http://localhost:8080/img/zapatillas/zapatilla9.avif")));
            productoRepository.save(new Producto("Nike", " Kidfinity", "VOI-000010", "Zapatillas de running de niños", new BigDecimal("59.99"), 2, new BigDecimal("0"), 5, false, "", Arrays.asList("http://localhost:8080/img/zapatillas/zapatilla10.jpg")));
            productoRepository.save(new Producto("Boreal", " Inigoat", "VOI-000011", "Para ser una cabra montesa como Íñigo ", new BigDecimal("59.99"), 2, new BigDecimal("0"), 5, false, "", Arrays.asList("http://localhost:8080/img/zapatillas/zapatilla11.avif")));


        } catch (Exception e) {
            System.err.println("Omitida carga de datos de Coches: Datos repetidos");
        }

    }
}
