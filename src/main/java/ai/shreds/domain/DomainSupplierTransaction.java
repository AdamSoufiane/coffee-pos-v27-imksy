package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * Represents the overall transaction related to a supplier, including multiple products.
 */
@Data
@Entity
@Table(name = "supplier_transaction")
@NoArgsConstructor
@AllArgsConstructor
public class DomainSupplierTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(name = "supplier_id", nullable = false)
    private UUID supplierId;

    @NotNull
    @Column(name = "transaction_date", nullable = false)
    private Timestamp transactionDate;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "supplierTransaction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DomainProductTransaction> products;

    /**
     * Calculates the total amount of the transaction by summing up the prices of the products multiplied by their quantities.
     *
     * @return the total amount of the transaction
     */
    public BigDecimal calculateTotalAmount() {
        return products.stream()
                .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}