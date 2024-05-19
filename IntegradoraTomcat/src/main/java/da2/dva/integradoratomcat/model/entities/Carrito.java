package da2.dva.integradoratomcat.model.entities;

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
@NoArgsConstructor
@AllArgsConstructor
//persistimos en mySQL

@Entity
public class Carrito {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID carrito_id;

    private LocalDate fechaCreacion;

    @OneToOne
    @MapsId
    private Cliente cliente;

    @Transient
    private BigDecimal precio;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
    private Set<LineaCarrito> lineas = new HashSet<>();

}
