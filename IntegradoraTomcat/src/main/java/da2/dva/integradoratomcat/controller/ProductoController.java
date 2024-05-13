package da2.dva.integradoratomcat.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import da2.dva.integradoratomcat.model.entities.Producto;
import da2.dva.integradoratomcat.repositories.mongo.ProductoRepository;
import da2.dva.integradoratomcat.services.ServicioProducto;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ServicioProducto servicioProducto;

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
}
