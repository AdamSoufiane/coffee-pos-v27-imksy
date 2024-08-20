package ai.shreds.domain;

import java.math.BigDecimal;
import ai.shreds.domain.DomainSupplierTransaction;

/**
 * Interface for calculating the total amount of a supplier transaction.
 */
public interface DomainCalculateTotalAmountPort {
    /**
     * Calculates the total amount of the given supplier transaction.
     * 
     * @param supplierTransaction the supplier transaction
     * @return the total amount as BigDecimal
     */
    BigDecimal calculateTotalAmount(DomainSupplierTransaction supplierTransaction);
}