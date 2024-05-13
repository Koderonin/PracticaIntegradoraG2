package da2.dva.integradoratomcat.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.BsonString;
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
    private String modelo; // ¡Esto es el "nombre"!
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
    private String comentarios;

    public Producto (String marca, String modelo, String codigo, String descripcion, BigDecimal precio, int unidadesVendidas, BigDecimal gastoAcumulado, int cantidadAlmacen, boolean enOferta, String comentarios) {
        setMarca(marca);
        setModelo(modelo);
        setCodigo(codigo);
        setPrecio(precio);
        setDescripcion(descripcion);
        setUnidadesVendidas(unidadesVendidas);
        setGastoAcumulado(gastoAcumulado);
        setCantidadAlmacen(cantidadAlmacen);
        setEnOferta(enOferta);
        setComentarios(comentarios);
    }

//    private Auditoria auditoria;

}