package da2.dva.integradoratomcat.model.entities;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Producto {
    @MongoId
    private ObjectId id;
    private String codigo;
    private String descripcion;
    private BigDecimal precio;
    private int unidadesVendidas;
    private BigDecimal gastoAcumulado;
    private int cantidadAlmacen;
    private int umbralSolicitudProveedor;
    private int umbralOcultoEnTienda;
    private boolean enOferta;
    private BigDecimal descuento;
    private boolean esNovedad;
    private int valoracionProducto;
    private String marca;
    private String modelo;
    private String comentarios;

//    private Auditoria auditoria;

}
