package ai.shreds.infrastructure;

import ai.shreds.domain.DomainProductTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository interface for managing DomainProductTransaction entities.
 */
@Repository
public interface InfrastructureProductTransactionRepository extends JpaRepository<DomainProductTransaction, UUID> {
}