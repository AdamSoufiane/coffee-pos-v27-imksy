package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DomainProductEntity {

    @Id
    @NotNull
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Size(max = 255)
    @NotBlank(message = "Name cannot be blank")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    @Column(name = "description")
    private String description;

    @DecimalMin(value = "0.0", inclusive = false)
    @Column(name = "price")
    private BigDecimal price;

    @NotNull(message = "Category ID cannot be null")
    @Column(name = "category_id")
    private UUID category_id;

    @Min(0)
    @Column(name = "stock_quantity")
    private int stock_quantity;

    @Version
    @Column(name = "version")
    private int version;

    // Additional validation can be added here if needed
    public void validate() {
        if (price != null && price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be a positive value.");
        }
        if (stock_quantity < 0) {
            throw new IllegalArgumentException("Stock quantity must be a non-negative integer.");
        }
        // Add logic to validate category_id corresponds to an existing category in the system
        if (category_id == null) {
            throw new IllegalArgumentException("Category ID cannot be null.");
        }
    }
}