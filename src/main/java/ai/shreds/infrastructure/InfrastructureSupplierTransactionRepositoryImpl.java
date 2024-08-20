package ai.shreds.infrastructure;

import ai.shreds.domain.DomainRepository;
import ai.shreds.domain.DomainSupplierTransaction;
import ai.shreds.domain.DomainSaveSupplierTransactionInputPort;
import ai.shreds.infrastructure.exceptions.InfrastructureSaveSupplierTransactionException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the DomainSaveSupplierTransactionInputPort interface.
 * Handles saving SupplierTransaction entities to the database.
 */
@Repository
@RequiredArgsConstructor
public class InfrastructureSupplierTransactionRepositoryImpl implements DomainSaveSupplierTransactionInputPort {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureSupplierTransactionRepositoryImpl.class);

    private final DomainRepository domainRepository;

    /**
     * Saves a SupplierTransaction entity to the database.
     * @param supplierTransaction the SupplierTransaction entity to be saved
     */
    @Override
    @Transactional
    public void save(DomainSupplierTransaction supplierTransaction) {
        try {
            logger.info("Saving supplier transaction with ID: {}", supplierTransaction.getId());
            domainRepository.save(supplierTransaction);
            logger.info("Supplier transaction with ID: {} saved successfully", supplierTransaction.getId());
        } catch (Exception e) {
            logger.error("Failed to save supplier transaction", e);
            throw new InfrastructureSaveSupplierTransactionException("Failed to save supplier transaction", e);
        }
    }
}