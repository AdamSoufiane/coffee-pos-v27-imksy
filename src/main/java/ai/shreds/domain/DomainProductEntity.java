package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    /**
     * Name of the product.
     */
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    /**
     * Description of the product.
     */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /**
     * Price of the product.
     */
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * Identifier for the category the product belongs to.
     */
    @Column(name = "category_id", nullable = false)
    private UUID categoryId;

    /**
     * Quantity of the product in stock.
     */
    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

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