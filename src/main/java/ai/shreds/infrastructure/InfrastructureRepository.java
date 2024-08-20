package ai.shreds.infrastructure;

import ai.shreds.domain.DomainRepository;
import ai.shreds.domain.DomainSupplierTransaction;
import ai.shreds.infrastructure.InfrastructureSaveSupplierTransactionException;
import ai.shreds.infrastructure.InfrastructureDatabaseException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class InfrastructureRepository implements DomainRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(DomainSupplierTransaction supplierTransaction) {
        try {
            entityManager.persist(supplierTransaction);
            supplierTransaction.getProducts().forEach(product -> entityManager.persist(product));
        } catch (Exception e) {
            throw new InfrastructureSaveSupplierTransactionException("Failed to save SupplierTransaction", e);
        }
    }

    @Override
    @Transactional
    public DomainSupplierTransaction findById(UUID id) {
        try {
            return entityManager.find(DomainSupplierTransaction.class, id);
        } catch (Exception e) {
            throw new InfrastructureDatabaseException("Failed to find SupplierTransaction by ID", e);
        }
    }
}