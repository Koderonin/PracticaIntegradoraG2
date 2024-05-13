package da2.dva.integradoratomcat.controller.REST;

import da2.dva.integradoratomcat.model.entities.Producto;
import da2.dva.integradoratomcat.repositories.mongo.ProductoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("producto")
public class ProductoControllerREST {
    @Autowired
    ProductoRepository productoRepository;

    @GetMapping("listado")
    public List<Producto> listadoProyectos() {
        return productoRepository.findAll();
    }

    @GetMapping("/detalle/{id}")
    public List<Producto> detalleProductoById(@PathVariable("id") String id) { return productoRepository.findById(new ObjectId(id)); }
}
