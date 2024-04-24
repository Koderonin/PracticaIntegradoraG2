package da2.dva.integradoratomcat.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "UsuarioCliente", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email", name = "usuario_cliente_email_uk")
})
@AttributeOverrides({
        @AttributeOverride(name = "id_usuario", column = @Column(name = "id_user_cliente"))
})
public class UsuarioCliente extends Usuario {
/*
    @OneToOne(mappedBy = "usuarioCliente", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)
    private Cliente cliente;
*/
}
