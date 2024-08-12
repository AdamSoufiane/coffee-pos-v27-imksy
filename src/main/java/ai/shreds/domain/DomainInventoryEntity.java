package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "Inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainInventoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "product_id", nullable = false)
    @NotNull
    private UUID productId;

    @Column(name = "available_quantity", nullable = false)
    @NotNull
    private Integer availableQuantity;

    @Column(name = "reserved_quantity", nullable = false)
    @NotNull
    private Integer reservedQuantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainInventoryEntity that = (DomainInventoryEntity) o;
        return productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return productId.hashCode();
    }
}