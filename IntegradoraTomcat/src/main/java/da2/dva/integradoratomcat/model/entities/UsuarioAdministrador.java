package da2.dva.integradoratomcat.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "UsuarioAdministrador", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email", name = "usuario_administrador_email_uk")
})
@AttributeOverrides({
        @AttributeOverride(name = "id_usuario", column = @Column(name = "id_administrador"))
})
public class UsuarioAdministrador extends Usuario {

/*
    @ManyToMany
    private Set<Auditoria> auditorias = new HashSet<>();
*/
}
