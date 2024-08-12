package ai.shreds.domain;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a product entity in the domain layer.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
@EntityListeners(AuditingEntityListener.class)
public class DomainProductEntity {

    /**
     * Unique identifier for the product.
     */
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    @NotNull
    private UUID id;

    /**
     * Name of the product.
     */
    @Column(name = "name", nullable = false, length = 255)
    @NotNull
    @Size(max = 255)
    private String name;

    /**
     * Description of the product.
     */
    @Column(name = "description", columnDefinition = "TEXT")
    @Size(max = 65535)
    private String description;

    /**
     * Price of the product.
     */
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    @NotNull
    private BigDecimal price;

    /**
     * Identifier for the category the product belongs to.
     */
    @Column(name = "category_id", nullable = false)
    @NotNull
    private UUID categoryId;

    /**
     * Quantity of the product in stock.
     */
    @Column(name = "stock_quantity", nullable = false)
    @NotNull
    private int stockQuantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainProductEntity that = (DomainProductEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}