package ai.shreds.infrastructure;

import ai.shreds.domain.DomainClientEntity;
import ai.shreds.domain.DomainAddressValue;
import ai.shreds.domain.DomainContactInfoValue;
import org.springframework.stereotype.Component;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * Mapper class for converting between InfrastructureClientEntity and DomainClientEntity.
 */
@Component
public class InfrastructureClientEntityMapper {

    /**
     * Converts an InfrastructureClientEntity to a DomainClientEntity.
     *
     * @param entity the InfrastructureClientEntity to convert
     * @return the converted DomainClientEntity
     */
    public DomainClientEntity toDomain(InfrastructureClientEntity entity) {
        if (entity == null) {
            return null;
        }
        return new DomainClientEntity(
                entity.getClientId(),
                entity.getFirstName(),
                entity.getLastName(),
                new DomainContactInfoValue(entity.getPhoneNumber(), entity.getEmail()),
                new DomainAddressValue(entity.getAddress(), entity.getZipCode(), entity.getCity()),
                entity.getRegistrationDate()
        );
    }

    /**
     * Converts a DomainClientEntity to an InfrastructureClientEntity.
     *
     * @param entity the DomainClientEntity to convert
     * @return the converted InfrastructureClientEntity
     */
    public InfrastructureClientEntity toInfrastructure(DomainClientEntity entity) {
        if (entity == null) {
            return null;
        }
        InfrastructureClientEntity infrastructureEntity = new InfrastructureClientEntity();
        infrastructureEntity.setClientId(entity.getClientId());
        infrastructureEntity.setFirstName(entity.getFirstName());
        infrastructureEntity.setLastName(entity.getLastName());
        infrastructureEntity.setEmail(entity.getContactInfo().getEmail());
        infrastructureEntity.setPhoneNumber(entity.getContactInfo().getPhone());
        infrastructureEntity.setAddress(entity.getAddress().getAddress());
        infrastructureEntity.setZipCode(entity.getAddress().getZip_code());
        infrastructureEntity.setCity(entity.getAddress().getCity());
        infrastructureEntity.setRegistrationDate(entity.getRegistrationDate());
        return infrastructureEntity;
    }
}