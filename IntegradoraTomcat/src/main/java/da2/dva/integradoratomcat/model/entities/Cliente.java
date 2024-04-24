package da2.dva.integradoratomcat.model.entities;

import da2.dva.integradoratomcat.model.auxiliar.Direccion;
import da2.dva.integradoratomcat.model.auxiliar.Pais;
import da2.dva.integradoratomcat.model.auxiliar.TarjetaCredito;
import da2.dva.integradoratomcat.utils.CheckColeccion;
import da2.dva.integradoratomcat.utils.CheckMayor18;
import da2.dva.integradoratomcat.utils.DatosContacto;
import da2.dva.integradoratomcat.utils.DatosPersonales;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_cliente;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "FK_USUARIO_CLIENTE"))
    // @MapsId
    private UsuarioCliente usuarioCliente;
    @Column(name = "nombre", length = 20)
    @NotBlank(groups = DatosPersonales.class)
    private String nombre;
    @NotBlank(groups = DatosPersonales.class)
    private String apellidos;

    @NotNull(groups = DatosPersonales.class)
    @CheckColeccion(coleccion = "listageneros")
    private String genero;

    @NotNull(groups = DatosPersonales.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @CheckMayor18(groups = DatosPersonales.class)
    private LocalDate fechaNacimiento;
    @NotBlank(groups = DatosPersonales.class)
    @CheckColeccion(coleccion = "listapaises")
    @OneToOne
    @JoinColumn(name = "pais", referencedColumnName = "siglas", foreignKey = @ForeignKey(name = "FK_PAIS_NACIMIENTO"))
    private Pais pais;
    @NotNull(groups = DatosPersonales.class)
    @CheckColeccion(coleccion = "listatiposDocumentos")
    @Column(name = "tipo_documento", length = 3)
    private String tipoDocumento;
    @Column(name = "num_documento", length = 10)
    @NotBlank(groups = DatosPersonales.class)
    private String documento;
    @NotBlank(groups = DatosContacto.class)
    @Pattern(regexp = "[0-9]{9}")
    @Column(name = "telefono_movil", length = 9)
    private String telefonoMovil;
    private BigDecimal gastoAcumuladoCliente;
    @OneToOne
    @JoinColumn(name = "id_direccion_postal", referencedColumnName = "id_direccion", foreignKey = @ForeignKey(name = "FK_DIRECCION_CLIENTE"))
    private Direccion direccion;
    @OneToMany
    @JoinTable(name="direcciones_entrega",
            joinColumns = @JoinColumn(name = "id_cliente", foreignKey = @ForeignKey(name = "FK_CLIENTE_DIRECCION_ENTREGA_ID_CLIENTE")),
            inverseJoinColumns = @JoinColumn(name = "id_direccion", foreignKey = @ForeignKey(name = "FK_DIRECCION_DIRECCION_ENTREGA_ID_DIRECCION")))
    private Set<Direccion> direccionEntrega = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL)
    private Set<TarjetaCredito> tarjetasCredito = new HashSet<>();
    private String tipoCliente;
    private String comentarios;

    @NotNull
    @AssertTrue
    private Boolean aceptacionLicencia;


    private LocalDate fechaBajaEntidad; // si null, no se ha dado de baja; es para borrado lógico

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Set<Pedido> pedidos = new HashSet<>();
}
