package da2.dva.integradoratomcat.model.entities;

import da2.dva.integradoratomcat.model.auxiliar.Direccion;
import da2.dva.integradoratomcat.model.auxiliar.Pais;
import da2.dva.integradoratomcat.model.auxiliar.TarjetaCredito;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {
/*ojo que esto no funciona BIEN
* se persiste el cliente a la vez que el usuario.
* Si se busca un usuario que persistir, no importa lo que haga,
* el usuario persistido está "detached"*/

    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_user_cliente", foreignKey = @ForeignKey(name = "FK_USUARIO_CLIENTE"))
    @MapsId
    private UsuarioCliente usuarioCliente;

    private String genero;
    private LocalDate fechaNacimiento;
    @OneToOne
    @JoinColumn(name = "pais", referencedColumnName = "siglas", foreignKey = @ForeignKey(name = "FK_PAIS_NACIMIENTO"))
    private Pais paisNacimiento;
    @Column(name = "tipo_documento", length = 3)
    private String tipoDocumento;
    @Column(name = "num_documento", length = 10)
    private String documento;
    @Column(name = "telefono_movil", length = 9)
    private String telefonoMovil;
    @Column(name = "nombre", length = 20)
    private String nombre;
    @Column(name = "apellidos", length = 30)
    private String apellidos;
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
    private Boolean aceptacionLicencia;

    private LocalDate fechaBajaEntidad; // si null, no se ha dado de baja; es para borrado lógico

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Set<Pedido> pedidos = new HashSet<>();
}

