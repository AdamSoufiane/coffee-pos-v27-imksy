package infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ClientJpaRepository extends JpaRepository<InfrastructureClientEntity, UUID> {
}