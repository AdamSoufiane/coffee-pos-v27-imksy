package ai.shreds.infrastructure;

import ai.shreds.domain.DomainSupplierEntity;
import ai.shreds.domain.DomainSupplierRepositoryPort;
import ai.shreds.domain.DomainContactInfoValue;
import ai.shreds.domain.DomainAddressValue;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;

import ai.shreds.infrastructure.jpa.SupplierJpaRepository;
import ai.shreds.infrastructure.jpa.SupplierEntity;

@Repository
@RequiredArgsConstructor
public class InfrastructureSupplierRepositoryImpl implements DomainSupplierRepositoryPort {

    private final SupplierJpaRepository supplierJpaRepository;
    private static final Logger logger = LoggerFactory.getLogger(InfrastructureSupplierRepositoryImpl.class);

    @Override
    @Transactional
    public void save(DomainSupplierEntity supplier) {
        try {
            ai.shreds.infrastructure.jpa.SupplierEntity supplierEntity = mapToSupplierEntity(supplier);
            supplierJpaRepository.save(supplierEntity);
        } catch (Exception e) {
            logger.error("Error saving supplier: ", e);
            throw new RuntimeException("Error saving supplier", e);
        }
    }

    @Override
    public DomainSupplierEntity findById(Long id) {
        try {
            return supplierJpaRepository.findById(id)
                .map(this::mapToDomainSupplierEntity)
                .orElseThrow(() -> new EntityNotFoundException("Supplier with ID " + id + " not found"));
        } catch (Exception e) {
            logger.error("Error finding supplier by ID: ", e);
            throw new RuntimeException("Error finding supplier by ID", e);
        }
    }

    @Override
    public List<DomainSupplierEntity> findAll() {
        try {
            List<ai.shreds.infrastructure.jpa.SupplierEntity> supplierEntities = supplierJpaRepository.findAll();
            return supplierEntities.stream().map(this::mapToDomainSupplierEntity).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error finding all suppliers: ", e);
            throw new RuntimeException("Error finding all suppliers", e);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            supplierJpaRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting supplier by ID: ", e);
            throw new RuntimeException("Error deleting supplier by ID", e);
        }
    }

    private ai.shreds.infrastructure.jpa.SupplierEntity mapToSupplierEntity(DomainSupplierEntity domainSupplierEntity) {
        if (domainSupplierEntity == null) return null;
        ai.shreds.infrastructure.jpa.SupplierEntity supplierEntity = new ai.shreds.infrastructure.jpa.SupplierEntity();
        supplierEntity.setId(domainSupplierEntity.getId());
        supplierEntity.setName(domainSupplierEntity.getName());
        supplierEntity.setRc(domainSupplierEntity.getRc());
        supplierEntity.setContactInfoPhone(domainSupplierEntity.getContactInfo().getPhone());
        supplierEntity.setContactInfoEmail(domainSupplierEntity.getContactInfo().getEmail());
        supplierEntity.setAddress(domainSupplierEntity.getAddress().getAddress());
        supplierEntity.setZipCode(domainSupplierEntity.getAddress().getZipCode());
        supplierEntity.setCity(domainSupplierEntity.getAddress().getCity());
        supplierEntity.setEcheanceDate(domainSupplierEntity.getEcheanceDate());
        supplierEntity.setCreatedAt(domainSupplierEntity.getCreatedAt());
        supplierEntity.setUpdatedAt(domainSupplierEntity.getUpdatedAt());
        return supplierEntity;
    }

    private DomainSupplierEntity mapToDomainSupplierEntity(ai.shreds.infrastructure.jpa.SupplierEntity supplierEntity) {
        if (supplierEntity == null) return null;
        DomainSupplierEntity domainSupplierEntity = new DomainSupplierEntity();
        domainSupplierEntity.setId(supplierEntity.getId());
        domainSupplierEntity.setName(supplierEntity.getName());
        domainSupplierEntity.setRc(supplierEntity.getRc());
        domainSupplierEntity.setContactInfo(new DomainContactInfoValue(supplierEntity.getContactInfoPhone(), supplierEntity.getContactInfoEmail()));
        domainSupplierEntity.setAddress(new DomainAddressValue(supplierEntity.getAddress(), supplierEntity.getZipCode(), supplierEntity.getCity()));
        domainSupplierEntity.setEcheanceDate(supplierEntity.getEcheanceDate());
        domainSupplierEntity.setCreatedAt(supplierEntity.getCreatedAt());
        domainSupplierEntity.setUpdatedAt(supplierEntity.getUpdatedAt());
        return domainSupplierEntity;
    }
}