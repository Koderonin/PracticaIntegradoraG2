package da2.dva.integradoratomcat.controller.REST;

import da2.dva.integradoratomcat.model.entities.Producto;
import da2.dva.integradoratomcat.services.ServicioImagenes;
import da2.dva.integradoratomcat.services.ServicioProducto;
import jakarta.servlet.http.HttpServletRequest;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/producto")
public class ProductoControllerREST {

    @Autowired
    private ServicioProducto servicioProducto;

    // SÓLO MÉTODOS READ, ESTO ES PARTE DE LA -API DE CLIENTE-

    @GetMapping("listado")
    public List<Document> listadoProductos() {
        return servicioProducto.findAllDocuments();
    }

    @GetMapping("/{id}")
    public Document detalleProductoById(@PathVariable("id") String id) {
        try {
            return servicioProducto.findById(new ObjectId(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("detalle/{codigo}")
    public Document detalleProductoByCodigo(@PathVariable("codigo") String codigo) {
        try {
            return servicioProducto.findByCodigo(codigo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}