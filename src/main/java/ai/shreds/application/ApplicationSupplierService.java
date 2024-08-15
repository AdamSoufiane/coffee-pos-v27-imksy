package ai.shreds.application;

import ai.shreds.domain.*;
import ai.shreds.shared.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationSupplierService implements ApplicationCreateSupplierInputPort, ApplicationGetSupplierByIdInputPort, ApplicationGetAllSuppliersInputPort, ApplicationUpdateSupplierInputPort, ApplicationDeleteSupplierInputPort {

    private final DomainSupplierRepositoryPort domainSupplierRepositoryPort;
    private final DomainNotificationServicePort domainNotificationServicePort;

    @Override
    @Transactional
    public ApplicationSupplierDTO createSupplier(ApplicationCreateSupplierDTO dto) {
        DomainContactInfoValue contactInfo = new DomainContactInfoValue(dto.getContact_info_phone(), dto.getContact_info_email());
        DomainAddressValue address = new DomainAddressValue(dto.getAddress(), dto.getZip_code(), dto.getCity());
        DomainSupplierEntity supplier = new DomainSupplierEntity(null, dto.getName(), dto.getRc(), contactInfo, address, dto.getEcheance_date(), null, null);
        validateSupplierData(supplier);
        domainSupplierRepositoryPort.save(supplier);
        return toApplicationSupplierDTO(supplier);
    }

    @Override
    public ApplicationSupplierDTO getSupplierById(Long id) {
        Optional<DomainSupplierEntity> supplier = domainSupplierRepositoryPort.findById(id);
        if (supplier.isPresent()) {
            return toApplicationSupplierDTO(supplier.get());
        } else {
            throw new IllegalArgumentException("Supplier not found with ID: " + id);
        }
    }

    @Override
    public List<ApplicationSupplierDTO> getAllSuppliers() {
        List<DomainSupplierEntity> suppliers = domainSupplierRepositoryPort.findAll();
        return suppliers.stream().map(this::toApplicationSupplierDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ApplicationSupplierDTO updateSupplier(Long id, ApplicationUpdateSupplierDTO dto) {
        Optional<DomainSupplierEntity> existingSupplier = domainSupplierRepositoryPort.findById(id);
        if (existingSupplier.isPresent()) {
            existingSupplier.get().setName(dto.getName());
            existingSupplier.get().setRc(dto.getRc());
            existingSupplier.get().getContact_info().setPhone(dto.getContact_info_phone());
            existingSupplier.get().getContact_info().setEmail(dto.getContact_info_email());
            existingSupplier.get().getAddress().setAddress(dto.getAddress());
            existingSupplier.get().getAddress().setZip_code(dto.getZip_code());
            existingSupplier.get().getAddress().setCity(dto.getCity());
            existingSupplier.get().setEcheance_date(dto.getEcheance_date());
            validateSupplierData(existingSupplier.get());
            domainSupplierRepositoryPort.save(existingSupplier.get());
            domainNotificationServicePort.notifySupplierUpdate(existingSupplier.get());
            return toApplicationSupplierDTO(existingSupplier.get());
        } else {
            throw new IllegalArgumentException("Supplier not found with ID: " + id);
        }
    }

    @Override
    @Transactional
    public void deleteSupplier(Long id) {
        domainSupplierRepositoryPort.deleteById(id);
    }

    private void validateSupplierData(DomainSupplierEntity supplier) {
        // Validate that supplier name is provided and non-empty
        if (supplier.getName() == null || supplier.getName().isEmpty()) {
            throw new IllegalArgumentException("Supplier name must be provided and non-empty.");
        }
        // Validate phone number format
        if (!supplier.getContact_info().getPhone().matches("\\d{3}-\\d{3}-\\d{4}")) {
            throw new IllegalArgumentException("Invalid phone number format.");
        }
        // Validate email format
        if (!supplier.getContact_info().getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        // Validate echeance date is a future date
        if (supplier.getEcheance_date().before(new java.util.Date())) {
            throw new IllegalArgumentException("Echeance date must be a future date.");
        }
    }

    private ApplicationSupplierDTO toApplicationSupplierDTO(DomainSupplierEntity supplier) {
        return new ApplicationSupplierDTO(
                supplier.getId(),
                supplier.getName(),
                supplier.getContact_info().getPhone(),
                supplier.getContact_info().getEmail(),
                supplier.getAddress().getAddress(),
                supplier.getAddress().getZip_code(),
                supplier.getAddress().getCity(),
                supplier.getRc(),
                supplier.getEcheance_date(),
                supplier.getCreated_at(),
                supplier.getUpdated_at()
        );
    }
}