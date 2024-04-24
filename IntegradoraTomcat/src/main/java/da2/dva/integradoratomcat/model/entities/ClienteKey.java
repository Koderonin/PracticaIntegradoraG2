package da2.dva.integradoratomcat.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteKey implements java.io.Serializable {
    private UsuarioCliente usuarioCliente;
   /* private Long cliente_id;*/
}
