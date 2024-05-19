package da2.dva.integradoratomcat.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario_cliente", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email", name = "usuario_cliente_email_uk")
})
@AttributeOverrides({
        @AttributeOverride(name = "id_usuario", column = @Column(name = "id_user_cliente"))
})
@Data @NoArgsConstructor
public class UsuarioCliente extends Usuario {

    public UsuarioCliente(String email, String clave, Long preguntaRecuperacion, String respuestaRecuperacion) {
        super(email, clave, preguntaRecuperacion, respuestaRecuperacion, false);
    }

}
