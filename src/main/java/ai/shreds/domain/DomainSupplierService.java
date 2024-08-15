package ai.shreds.domain;

import ai.shreds.application.ApplicationCreateSupplierDTO;
import ai.shreds.application.ApplicationSupplierDTO;
import ai.shreds.application.ApplicationUpdateSupplierDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Date;
import java.time.LocalDateTime;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
@RequiredArgsConstructor
public class DomainSupplierService implements ApplicationCreateSupplierInputPort, ApplicationGetSupplierByIdInputPort, ApplicationGetAllSuppliersInputPort, ApplicationUpdateSupplierInputPort, ApplicationDeleteSupplierInputPort {
    private final DomainSupplierRepositoryPort domainSupplierRepositoryPort;
    private final DomainNotificationServicePort domainNotificationServicePort;
    private static final Logger logger = LoggerFactory.getLogger(DomainSupplierService.class);

    @Transactional
    public ApplicationSupplierDTO createSupplier(ApplicationCreateSupplierDTO dto) {
        validateSupplierData(dto);
        DomainSupplierEntity supplier = new DomainSupplierEntity();
        supplier.setName(dto.getName());
        supplier.setRc(dto.getRc());
        supplier.setContactInfo(new DomainContactInfoValue(dto.getContact_info_phone(), dto.getContact_info_email()));
        supplier.setAddress(new DomainAddressValue(dto.getAddress(), dto.getZip_code(), dto.getCity()));
        supplier.setEcheanceDate(dto.getEcheance_date());
        supplier.setCreatedAt(LocalDateTime.now());
        supplier.setUpdatedAt(LocalDateTime.now());
        domainSupplierRepositoryPort.save(supplier);
        logger.info("Created supplier with ID: {}", supplier.getId());
        return convertToApplicationSupplierDTO(supplier);
    }

    @Transactional(readOnly = true)
    public ApplicationSupplierDTO getSupplierById(Long id) {
        Optional<DomainSupplierEntity> supplier = domainSupplierRepositoryPort.findById(id);
        if (supplier.isPresent()) {
            logger.info("Retrieved supplier with ID: {}", id);
            return convertToApplicationSupplierDTO(supplier.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found.");
        }
    }

    @Transactional(readOnly = true)
    public List<ApplicationSupplierDTO> getAllSuppliers() {
        List<DomainSupplierEntity> suppliers = domainSupplierRepositoryPort.findAll();
        logger.info("Retrieved all suppliers");
        return suppliers.stream().map(this::convertToApplicationSupplierDTO).collect(Collectors.toList());
    }

    @Transactional
    public ApplicationSupplierDTO updateSupplier(Long id, ApplicationUpdateSupplierDTO dto) {
        Optional<DomainSupplierEntity> supplier = domainSupplierRepositoryPort.findById(id);
        if (supplier.isPresent()) {
            DomainSupplierEntity entity = supplier.get();
            entity.setName(dto.getName());
            entity.setRc(dto.getRc());
            entity.setContactInfo(new DomainContactInfoValue(dto.getContact_info_phone(), dto.getContact_info_email()));
            entity.setAddress(new DomainAddressValue(dto.getAddress(), dto.getZip_code(), dto.getCity()));
            entity.setEcheanceDate(dto.getEcheance_date());
            entity.setUpdatedAt(LocalDateTime.now());
            domainSupplierRepositoryPort.save(entity);
            domainNotificationServicePort.notifySupplierUpdate(entity);
            logger.info("Updated supplier with ID: {}", id);
            return convertToApplicationSupplierDTO(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found.");
        }
    }

    @Transactional
    public void deleteSupplier(Long id) {
        domainSupplierRepositoryPort.deleteById(id);
        domainNotificationServicePort.sendNotification("Supplier with ID " + id + " deleted.");
        logger.info("Deleted supplier with ID: {}", id);
    }

    private void validateSupplierData(ApplicationCreateSupplierDTO dto) {
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new IllegalArgumentException("Supplier name must be provided.");
        }
        if (!isValidContactInfo(dto.getContact_info_phone(), dto.getContact_info_email())) {
            throw new IllegalArgumentException("Invalid contact information.");
        }
        if (dto.getEcheance_date().before(new Date())) {
            throw new IllegalArgumentException("Echeance date must be a future date.");
        }
        if (domainSupplierRepositoryPort.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Supplier name must be unique.");
        }
    }

    private boolean isValidContactInfo(String phone, String email) {
        return phone.matches("\\d{3}-\\d{3}-\\d{4}") && email.contains("@");
    }

    private ApplicationSupplierDTO convertToApplicationSupplierDTO(DomainSupplierEntity supplier) {
        return ApplicationSupplierDTO.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .rc(supplier.getRc())
                .contact_info_phone(supplier.getContactInfo().getPhone())
                .contact_info_email(supplier.getContactInfo().getEmail())
                .address(supplier.getAddress().getAddress())
                .zip_code(supplier.getAddress().getZipCode())
                .city(supplier.getAddress().getCity())
                .echeance_date(supplier.getEcheanceDate())
                .created_at(supplier.getCreatedAt())
                .updated_at(supplier.getUpdatedAt())
                .build();
    }
}