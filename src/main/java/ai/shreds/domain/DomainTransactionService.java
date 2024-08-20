package ai.shreds.domain;

import ai.shreds.domain.exception.DomainSaveSupplierTransactionException;
import ai.shreds.domain.exception.DomainCalculateTotalAmountException;
import ai.shreds.domain.model.DomainSupplierTransaction;
import ai.shreds.domain.model.DomainProductTransaction;
import ai.shreds.domain.port.DomainSaveSupplierTransactionPort;
import ai.shreds.domain.port.DomainCalculateTotalAmountPort;
import ai.shreds.domain.repository.DomainRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

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
            throw new DomainSaveSupplierTransactionException("Error saving supplier transaction", e);
        }
    }

    /**
     * Calculates the total amount of a supplier transaction.
     * @param supplierTransaction The supplier transaction entity whose total amount is to be calculated.
     * @return The calculated total amount.
     * @throws DomainCalculateTotalAmountException if an error occurs while calculating the total amount.
     */
    @Override
    @Transactional
    public BigDecimal calculateTotalAmount(DomainSupplierTransaction supplierTransaction) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (DomainProductTransaction productTransaction : supplierTransaction.getProducts()) {
            BigDecimal productTotal = productTransaction.getPrice().multiply(BigDecimal.valueOf(productTransaction.getQuantity()));
            totalAmount = totalAmount.add(productTotal);
        }
        return totalAmount;
    }
}