package da2.dva.integradoratomcat.model.auxiliar;


import da2.dva.integradoratomcat.model.entities.Usuario;
import da2.dva.integradoratomcat.model.entities.UsuarioAdministrador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_auditoria;

    private LocalDate fechaAltaEntidad;
    private LocalDate fechaUltimaModificacionEntidad;
    private LocalDate fechaBorradoEntidad;

    @OneToOne
    @JoinColumn(referencedColumnName = "id_administrador", foreignKey = @ForeignKey(name = "FK_ADMINISTRADOR_ALTA"))
    private UsuarioAdministrador usuarioAdministradorQueRealizaAlta;
    @OneToOne
    @JoinColumn(referencedColumnName = "id_administrador", foreignKey = @ForeignKey(name = "FK_ADMINISTRADOR_MODIFICACION"))
    private UsuarioAdministrador usuarioAdministradorQueRealizaUltimaModificacion;
    @OneToOne
    @JoinColumn(referencedColumnName = "id_administrador", foreignKey = @ForeignKey(name = "FK_ADMINISTRADOR_ALTA"))
    private UsuarioAdministrador usuarioAdministradorQueRealizaBorrado;
}
