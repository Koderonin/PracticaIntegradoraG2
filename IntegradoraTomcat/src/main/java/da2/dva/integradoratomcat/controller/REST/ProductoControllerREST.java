package da2.dva.integradoratomcat.controller.REST;

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

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/producto")
public class ProductoControllerREST {

    @Autowired
    private ServicioProducto servicioProducto;

    @Autowired
    private ServicioImagenes servicioImagenes;

    // Create
    /**
     * Este método es el endpoint para crear un nuevo producto.
     * Sería interesante estudiar la posibilidad de retornar una respuesta al cliente con el resultado de la operación
     * (puede haber errores al persistir, por ejemplo).
     * */
    @PostMapping(consumes = "multipart/form-data")
    public void createProducto(@RequestPart("formularioProducto") Document formularioProducto,
                               @RequestPart(value = "imagenes", required = false) MultipartFile[] imagenes) {
        // si hay imagenes, las procesamos: guardamos en base de datos y una referencia en producto
        if (imagenes != null) {
            String[] imagenesArray = new String[imagenes.length];
            int i = 0;
            for (MultipartFile imagen : imagenes) {
                if (imagen != null && !imagen.isEmpty()) {
                    // TODO: guardar imagen (npi cómo, luego hay que encontrarla!)

                    // TODO: pasar la referencia de la imagen al documento que va a mongo, lo que hay es una pruebita
                    imagenesArray[i++] = imagen.getOriginalFilename();
                }
            }
            formularioProducto.append("imagenes", imagenesArray);
        }
        try {
            servicioProducto.addNuevoProducto(formularioProducto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read
    @GetMapping("listado")
    public List<Document> listadoProyectos() {
        return servicioProducto.findAllDocuments();
    }

    @GetMapping("/detalle/{codigo}")
    public Document detalleProductoByCodigo(@PathVariable("codigo") String codigo) {
        try {
            return servicioProducto.findByCodigo(codigo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
