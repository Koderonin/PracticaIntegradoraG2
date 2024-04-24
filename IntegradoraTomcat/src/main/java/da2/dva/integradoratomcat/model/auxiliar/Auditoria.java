package da2.dva.integradoratomcat.model.auxiliar;


import da2.dva.integradoratomcat.model.entities.UsuarioAdministrador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "auditoria")
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_auditoria;

    private LocalDate fechaAltaEntidad;
    private LocalDate fechaUltimaModificacionEntidad;
    private LocalDate fechaBorradoEntidad;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_admin_alta", referencedColumnName = "id_administrador", foreignKey = @ForeignKey(name = "FK_ADMINISTRADOR_ALTA"))
    private UsuarioAdministrador usuarioAdministradorQueRealizaAlta;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_admin_modificacion", referencedColumnName = "id_administrador", foreignKey = @ForeignKey(name = "FK_ADMINISTRADOR_MODIFICACION"))
    private UsuarioAdministrador usuarioAdministradorQueRealizaUltimaModificacion;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_admin_borrado", referencedColumnName = "id_administrador", foreignKey = @ForeignKey(name = "FK_ADMINISTRADOR_BORRADO"))
    private UsuarioAdministrador usuarioAdministradorQueRealizaBorrado;
}
