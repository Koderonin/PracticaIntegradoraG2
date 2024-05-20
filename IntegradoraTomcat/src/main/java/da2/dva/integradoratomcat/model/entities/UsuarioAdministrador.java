package da2.dva.integradoratomcat.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "UsuarioAdministrador", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email", name = "usuario_administrador_email_uk")
})
@AttributeOverrides({
        @AttributeOverride(name = "id_usuario", column = @Column(name = "id_administrador"))
})
@Data @AllArgsConstructor
public class UsuarioAdministrador extends Usuario {

    public UsuarioAdministrador(String email, String clave, Long preguntaRecuperacion, String respuestaRecuperacion) {
        super(email, clave, preguntaRecuperacion, respuestaRecuperacion, true);
    }

/*
    @ManyToMany
    private Set<Auditoria> auditorias = new HashSet<>();
*/
}
