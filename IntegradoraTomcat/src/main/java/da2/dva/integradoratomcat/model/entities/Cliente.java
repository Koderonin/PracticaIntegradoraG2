package da2.dva.integradoratomcat.model.entities;

import da2.dva.integradoratomcat.model.auxiliar.Direccion;
import da2.dva.integradoratomcat.model.collections.Genero;
import da2.dva.integradoratomcat.model.collections.Pais;
import da2.dva.integradoratomcat.model.auxiliar.TarjetaCredito;
import da2.dva.integradoratomcat.model.collections.TipoDocumento;
import da2.dva.integradoratomcat.utils.*;

import jakarta.persistence.*;
import jakarta.validation.Valid;
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

@Entity
@CheckDocumento
@Data @AllArgsConstructor @NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_usuario"}, name = "UK_CLIENTE_USUARIO"),
        @UniqueConstraint(columnNames = {"id_direccion_postal"}, name = "UK_CLIENTE_DIRECCION_POSTAL")
})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_cliente;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_user_cliente", foreignKey = @ForeignKey(name = "FK_USUARIO_CLIENTE"))
//    @MapsId("id_cliente")
    private UsuarioCliente usuarioCliente;

    @Column(name = "nombre", length = 20)
    @NotBlank(groups = DatosPersonales.class)
    private String nombre;

    @NotBlank(groups = DatosPersonales.class)
    private String apellidos;

    @NotNull(groups = DatosPersonales.class)
    @ManyToOne
    @JoinColumn(name = "genero", referencedColumnName = "siglas", foreignKey = @ForeignKey(name = "FK_GENERO_CLIENTE"))
    @Valid
    private Genero genero;

    @NotNull(groups = DatosPersonales.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @CheckMayor18(groups = DatosPersonales.class)
    private LocalDate fechaNacimiento;

    @NotNull(groups = DatosPersonales.class)
    @ManyToOne
    @JoinColumn(name = "pais", referencedColumnName = "siglas", foreignKey = @ForeignKey(name = "FK_PAIS_NACIMIENTO_CLIENTE"))
    @Valid
    private Pais paisNacimiento;

    @NotNull(groups = DatosPersonales.class)
    @ManyToOne
    @JoinColumn(name = "tipo_documento", referencedColumnName = "siglas", foreignKey = @ForeignKey(name = "FK_TIPO_DOCUMENTO_CLIENTE"))
    @Valid
    private TipoDocumento tipoDocumento;

    @NotBlank(groups = DatosPersonales.class)
    @Column(name = "num_documento", length = 14)
    private String documento;

    @NotNull(groups = DatosContacto.class)
    @Pattern(regexp = "[0-9]{9}" , groups = DatosContacto.class)
    @Column(name = "telefono_movil", length = 9)
    private String telefonoMovil;

    private BigDecimal gastoAcumuladoCliente;

    @Valid
    @OneToOne
    @JoinColumn(name = "id_direccion_postal", referencedColumnName = "id_direccion", foreignKey = @ForeignKey(name = "FK_DIRECCION_CLIENTE"))
    private Direccion direccion;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name="direcciones_entrega",
            joinColumns = @JoinColumn(name = "id_cliente", foreignKey = @ForeignKey(name = "FK_CLIENTE_DIRECCIONES_ENTREGA_ID_CLIENTE")),
            inverseJoinColumns = @JoinColumn(name = "id_direccion", foreignKey = @ForeignKey(name = "FK_DIRECCION_DIRECCIONES_ENTREGA_ID_DIRECCION")))
    private Set<Direccion> direccionEntrega = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="tarjetas_credito",
            joinColumns = @JoinColumn(name = "id_cliente", foreignKey = @ForeignKey(name = "FK_CLIENTE_TARJETAS_CREDITO_ID_CLIENTE")),
            inverseJoinColumns = @JoinColumn(name = "id_tarjeta", foreignKey = @ForeignKey(name = "FK_TARJETA_CREDITO_TARJETAS_CREDITO_ID_TARJETA")))
    private Set<@Valid TarjetaCredito> tarjetasCredito = new HashSet<>();

    private String tipoCliente;
    private String comentarios;

    @NotNull(groups = DatosCliente.class)
    @CheckLicencia(groups = DatosCliente.class)
    private Boolean aceptacionLicencia;

    private LocalDate fechaBajaEntidad; // si null, no se ha dado de baja; es para borrado lógico

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.EAGER)
    private Set<Pedido> pedidos = new HashSet<>();

    public Cliente(UsuarioCliente usuarioCliente, String nombre, String apellidos, Genero genero, Pais paisNacimiento, LocalDate fechaNacimiento, TipoDocumento tipoDocumento, String documento, String telefonoMovil, Direccion direccion) {
        setUsuarioCliente(usuarioCliente);
        setNombre(nombre);
        setApellidos(apellidos);
        setGenero(genero);
        setPaisNacimiento(paisNacimiento);
        setFechaNacimiento(fechaNacimiento);
        setTipoDocumento(tipoDocumento);
        setDocumento(documento);
        setTelefonoMovil(telefonoMovil);
        setDireccion(direccion);
        setAceptacionLicencia(true);
    }
}

