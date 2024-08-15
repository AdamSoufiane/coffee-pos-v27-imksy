package ai.shreds.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierJpaRepository extends JpaRepository<DomainSupplierEntity, Long> {
}