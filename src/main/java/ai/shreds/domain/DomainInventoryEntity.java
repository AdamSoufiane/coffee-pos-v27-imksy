package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Inventory")
public class DomainInventoryEntity {

    @Id
    @NotNull
    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @NotNull
    @Column(name = "available_quantity", nullable = false)
    private Integer availableQuantity;

    @NotNull
    @Column(name = "reserved_quantity", nullable = false)
    private Integer reservedQuantity;

    @NotNull
    @Size(max = 255)
    @Column(name = "warehouse_location", nullable = false, length = 255)
    private String warehouseLocation;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_checked", nullable = false)
    private Timestamp lastChecked;
}