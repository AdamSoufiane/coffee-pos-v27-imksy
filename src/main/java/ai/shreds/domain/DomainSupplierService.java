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
import java.util.stream.Collectors;
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
        logger.info("Supplier created with name: " + dto.getName());
        return new ApplicationSupplierDTO(supplier.getId(), supplier.getName(), supplier.getRc(), supplier.getContactInfo().getPhone(), supplier.getContactInfo().getEmail(), supplier.getAddress().getAddress(), supplier.getAddress().getZipCode(), supplier.getAddress().getCity(), supplier.getEcheanceDate(), supplier.getCreatedAt(), supplier.getUpdatedAt());
    }

    @Transactional(readOnly = true)
    public ApplicationSupplierDTO getSupplierById(Long id) {
        DomainSupplierEntity supplier = domainSupplierRepositoryPort.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found"));
        return new ApplicationSupplierDTO(supplier.getId(), supplier.getName(), supplier.getRc(), supplier.getContactInfo().getPhone(), supplier.getContactInfo().getEmail(), supplier.getAddress().getAddress(), supplier.getAddress().getZipCode(), supplier.getAddress().getCity(), supplier.getEcheanceDate(), supplier.getCreatedAt(), supplier.getUpdatedAt());
    }

    @Transactional(readOnly = true)
    public List<ApplicationSupplierDTO> getAllSuppliers() {
        return domainSupplierRepositoryPort.findAll().stream().map(supplier -> new ApplicationSupplierDTO(supplier.getId(), supplier.getName(), supplier.getRc(), supplier.getContactInfo().getPhone(), supplier.getContactInfo().getEmail(), supplier.getAddress().getAddress(), supplier.getAddress().getZipCode(), supplier.getAddress().getCity(), supplier.getEcheanceDate(), supplier.getCreatedAt(), supplier.getUpdatedAt())).collect(Collectors.toList());
    }

    @Transactional
    public ApplicationSupplierDTO updateSupplier(Long id, ApplicationUpdateSupplierDTO dto) {
        DomainSupplierEntity supplier = domainSupplierRepositoryPort.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found"));
        supplier.setName(dto.getName());
        supplier.setRc(dto.getRc());
        supplier.setContactInfo(new DomainContactInfoValue(dto.getContact_info_phone(), dto.getContact_info_email()));
        supplier.setAddress(new DomainAddressValue(dto.getAddress(), dto.getZip_code(), dto.getCity()));
        supplier.setEcheanceDate(dto.getEcheance_date());
        supplier.setUpdatedAt(LocalDateTime.now());
        domainSupplierRepositoryPort.save(supplier);
        logger.info("Supplier updated with id: " + id);
        return new ApplicationSupplierDTO(supplier.getId(), supplier.getName(), supplier.getRc(), supplier.getContactInfo().getPhone(), supplier.getContactInfo().getEmail(), supplier.getAddress().getAddress(), supplier.getAddress().getZipCode(), supplier.getAddress().getCity(), supplier.getEcheanceDate(), supplier.getCreatedAt(), supplier.getUpdatedAt());
    }

    @Transactional
    public void deleteSupplier(Long id) {
        DomainSupplierEntity supplier = domainSupplierRepositoryPort.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found"));
        domainSupplierRepositoryPort.delete(supplier);
        logger.info("Supplier deleted with id: " + id);
    }

    private void validateSupplierData(ApplicationCreateSupplierDTO dto) {
        // Add validation logic here
    }
}