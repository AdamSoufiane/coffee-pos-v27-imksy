package ai.shreds.infrastructure;

import ai.shreds.domain.DomainProductTransaction;
import ai.shreds.domain.DomainSaveProductTransactionInputPort;
import ai.shreds.infrastructure.repository.ProductTransactionRepository;
import ai.shreds.infrastructure.exception.InfrastructureSaveProductTransactionException;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

/**
 * Implementation of DomainSaveProductTransactionInputPort for saving product transactions.
 */
@Repository
@RequiredArgsConstructor
public class InfrastructureProductTransactionRepositoryImpl implements DomainSaveProductTransactionInputPort {

    private final ProductTransactionRepository productTransactionRepository;

    /**
     * Saves a product transaction to the database.
     *
     * @param productTransaction the product transaction to save
     */
    @Override
    public void save(DomainProductTransaction productTransaction) {
        try {
            productTransactionRepository.save(productTransaction);
        } catch (Exception e) {
            throw new InfrastructureSaveProductTransactionException("Failed to save product transaction", e);
        }
    }
}