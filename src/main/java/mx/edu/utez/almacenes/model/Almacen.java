package mx.edu.utez.almacenes.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Almacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clave;

    private LocalDate fechaRegistro;

    private BigDecimal precioVenta;

    private BigDecimal precioRenta;

    @Enumerated(EnumType.STRING)
    private Tamano tamano;

    private String estado;

    @ManyToOne
    @JoinColumn(name = "sede_id")
    private Sede sede;

    @PrePersist
    public void prePersist() {
        this.estado = "DISPONIBLE";
    }
}
