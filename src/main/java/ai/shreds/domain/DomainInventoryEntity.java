package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;
import java.util.Date;

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
    @Column(name = "warehouse_location", nullable = false, length = 255)
    private String warehouseLocation;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_checked", nullable = false)
    private Date lastChecked;
}