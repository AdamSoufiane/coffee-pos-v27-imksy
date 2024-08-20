package ai.shreds.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service class for handling supplier transactions.
 * Implements DomainSaveSupplierTransactionPort and DomainCalculateTotalAmountPort.
 */
@Service
public class DomainTransactionService implements DomainSaveSupplierTransactionPort, DomainCalculateTotalAmountPort {

    private final DomainRepository domainRepository;

    public DomainTransactionService(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    /**
     * Saves a supplier transaction entity to the database.
     * @param supplierTransaction The supplier transaction entity to be saved.
     * @throws DomainSaveSupplierTransactionException if an error occurs while saving the transaction.
     */
    @Override
    @Transactional
    public void saveSupplierTransaction(DomainSupplierTransaction supplierTransaction) {
        try {
            domainRepository.save(supplierTransaction);
        } catch (Exception e) {
            throw new DomainSaveSupplierTransactionException("Failed to save supplier transaction", e);
        }
    }

    /**
     * Calculates the total amount of a supplier transaction.
     * @param supplierTransaction The supplier transaction entity whose total amount is to be calculated.
     * @return The calculated total amount.
     * @throws DomainCalculateTotalAmountException if an error occurs while calculating the total amount.
     */
    @Override
    public BigDecimal calculateTotalAmount(DomainSupplierTransaction supplierTransaction) {
        try {
            BigDecimal totalAmount = BigDecimal.ZERO;
            List<DomainProductTransaction> products = supplierTransaction.getProducts();
            for (DomainProductTransaction productTransaction : products) {
                BigDecimal productTotal = productTransaction.getPrice().multiply(BigDecimal.valueOf(productTransaction.getQuantity()));
                totalAmount = totalAmount.add(productTotal);
            }
            return totalAmount;
        } catch (Exception e) {
            throw new DomainCalculateTotalAmountException("Failed to calculate total amount", e);
        }
    }
}