package ai.shreds.infrastructure;

import ai.shreds.domain.DomainProductTransaction;
import ai.shreds.domain.DomainSaveProductTransactionInputPort;
import ai.shreds.infrastructure.InfrastructureProductTransactionRepository;
import ai.shreds.infrastructure.InfrastructureSaveProductTransactionException;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

/**
 * Implementation of DomainSaveProductTransactionInputPort for saving product transactions.
 */
@Repository
@RequiredArgsConstructor
public class InfrastructureProductTransactionRepositoryImpl implements DomainSaveProductTransactionInputPort {

    private final InfrastructureProductTransactionRepository productTransactionRepository;

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