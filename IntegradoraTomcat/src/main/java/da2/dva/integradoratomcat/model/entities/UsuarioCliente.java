package da2.dva.integradoratomcat.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
//@AllArgsConstructor
@NoArgsConstructor
@Entity
@SecondaryTable(name = "Cliente", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id_user_cliente"))
@Table(name = "UsuarioCliente", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email", name = "usuario_cliente_email_uk")
})
@AttributeOverrides({
        @AttributeOverride(name = "id_usuario", column = @Column(name = "id_user_cliente"))
})
public class UsuarioCliente extends Usuario {

    public UsuarioCliente(UUID id_usuario, String email, String clave, String confirmClave, Long preguntaRecuperacion, String respuestaRecuperacion) {
        super(id_usuario, email, clave, confirmClave, preguntaRecuperacion, respuestaRecuperacion);
    }

    public UsuarioCliente(String email, String clave, Long preguntaRecuperacion, String respuestaRecuperacion) {
        super(email, clave, preguntaRecuperacion, respuestaRecuperacion);
    }
/*
    @OneToOne(mappedBy = "usuarioCliente", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)
    private Cliente cliente;
*/
}
