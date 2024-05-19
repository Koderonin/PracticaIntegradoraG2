package da2.dva.integradoratomcat.controller.REST;

import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.services.ServicioCliente;
import da2.dva.integradoratomcat.services.ServicioImagenes;
import da2.dva.integradoratomcat.services.ServicioProducto;
import da2.dva.integradoratomcat.services.ServicioUsuario;
import jakarta.servlet.http.HttpSession;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost", "http://apache.da2.dva", "http://tomcat.da2.dva", "http://localhost:8080", "http://localhost:8081"}
        , allowCredentials = "true")
@RequestMapping("admin/api/producto")
public class AdminProductoControllerREST {

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
                    // guardar imagen en una carpeta local
                    Path path = Paths.get("/usr/local/tomcat/webapps/ROOT/WEB-INF/classes/static/img", imagen.getOriginalFilename());
                    try {
                        Files.write(path, imagen.getBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // pasar la referencia de la imagen al documento que va a mongo TODO: SE JODEN LAS COSAS SI HAY ESPACIOS, HAY QUE PROCESAR EL NOMBRE
                    String pathString = "http://localhost:8080/img/" + imagen.getOriginalFilename();
                    imagenesArray[i++] = pathString;
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
