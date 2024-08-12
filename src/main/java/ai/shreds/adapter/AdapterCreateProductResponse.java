package ai.shreds.adapter;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AdapterCreateProductResponse encapsulates the response data for the create product API endpoint.
 * It includes the status of the operation, the unique identifier of the newly created product,
 * and a message providing additional information about the result.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class AdapterCreateProductResponse {

    /**
     * Status of the product creation operation (e.g., success or failure).
     */
    private String status;

    /**
     * Unique identifier of the newly created product.
     */
    private UUID productId;

    /**
     * Additional information about the result of the operation.
     */
    private String message;
}

package ai.shreds.adapter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdapterCreateProductRequest {
    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @Size(max = 500)
    private String description;

    @NotNull
    @Min(value = 0, message = "Price must be positive")
    private BigDecimal price;

    @NotNull
    private UUID categoryId;

    @NotNull
    @Min(value = 0, message = "Stock quantity must be non-negative")
    private Integer stockQuantity;
}

package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedRequestParams {
    @NotBlank
    private String name;

    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private UUID categoryId;

    @NotNull
    @Positive
    private Integer stockQuantity;
}

package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedProductDTO {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private UUID categoryId;
    private int stockQuantity;
}

package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
@EntityListeners(AuditingEntityListener.class)
public class DomainProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "category_id", nullable = false)
    private UUID categoryId;

    @Column(name = "stock_quantity", nullable = false)
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