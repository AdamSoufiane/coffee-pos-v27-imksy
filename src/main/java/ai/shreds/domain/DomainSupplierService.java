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
 import java.util.Date; 
  
 @Service 
 @RequiredArgsConstructor 
 public class DomainSupplierService { 
     private final DomainSupplierRepositoryPort domainSupplierRepositoryPort; 
     private final DomainNotificationServicePort domainNotificationServicePort; 
     private static final Logger logger = LoggerFactory.getLogger(DomainSupplierService.class); 
  
     @Transactional 
     public ApplicationSupplierDTO createSupplier(ApplicationCreateSupplierDTO dto) { 
         // Validate input data 
         validateSupplierData(dto); 
          
         // Create new supplier entity 
         DomainSupplierEntity supplier = new DomainSupplierEntity(); 
         supplier.setName(dto.getName()); 
         supplier.setRc(dto.getRc()); 
         supplier.setContactInfo(new DomainContactInfoValue(dto.getContact_info_phone(), dto.getContact_info_email())); 
         supplier.setAddress(new DomainAddressValue(dto.getAddress(), dto.getZip_code(), dto.getCity())); 
         supplier.setEcheanceDate(dto.getEcheance_date()); 
          
         // Save supplier entity 
         domainSupplierRepositoryPort.save(supplier); 
          
         // Log creation 
         logger.info("Created supplier with ID: {}", supplier.getId()); 
          
         // Convert to ApplicationSupplierDTO 
         return convertToApplicationSupplierDTO(supplier); 
     } 
  
     @Transactional(readOnly = true) 
     public ApplicationSupplierDTO getSupplierById(Long id) { 
         // Retrieve supplier entity by ID 
         DomainSupplierEntity supplier = domainSupplierRepositoryPort.findById(id); 
          
         // Log retrieval 
         logger.info("Retrieved supplier with ID: {}", id); 
          
         // Convert to ApplicationSupplierDTO 
         return convertToApplicationSupplierDTO(supplier); 
     } 
  
     @Transactional(readOnly = true) 
     public List<ApplicationSupplierDTO> getAllSuppliers() { 
         // Retrieve all supplier entities 
         List<DomainSupplierEntity> suppliers = domainSupplierRepositoryPort.findAll(); 
          
         // Log retrieval 
         logger.info("Retrieved all suppliers"); 
          
         // Convert to list of ApplicationSupplierDTOs 
         return suppliers.stream() 
                 .map(this::convertToApplicationSupplierDTO) 
                 .collect(Collectors.toList()); 
     } 
  
     @Transactional 
     public ApplicationSupplierDTO updateSupplier(Long id, ApplicationUpdateSupplierDTO dto) { 
         // Retrieve existing supplier entity 
         DomainSupplierEntity supplier = domainSupplierRepositoryPort.findById(id); 
          
         // Update supplier entity fields 
         supplier.setName(dto.getName()); 
         supplier.setRc(dto.getRc()); 
         supplier.setContactInfo(new DomainContactInfoValue(dto.getContact_info_phone(), dto.getContact_info_email())); 
         supplier.setAddress(new DomainAddressValue(dto.getAddress(), dto.getZip_code(), dto.getCity())); 
         supplier.setEcheanceDate(dto.getEcheance_date()); 
          
         // Save updated supplier entity 
         domainSupplierRepositoryPort.save(supplier); 
          
         // Notify about supplier update 
         domainNotificationServicePort.notifySupplierUpdate(supplier); 
          
         // Log update 
         logger.info("Updated supplier with ID: {}", id); 
          
         // Convert to ApplicationSupplierDTO 
         return convertToApplicationSupplierDTO(supplier); 
     } 
  
     @Transactional 
     public void deleteSupplier(Long id) { 
         // Delete supplier entity by ID 
         domainSupplierRepositoryPort.deleteById(id); 
          
         // Notify about supplier deletion 
         domainNotificationServicePort.sendNotification("Supplier with ID " + id + " deleted."); 
          
         // Log deletion 
         logger.info("Deleted supplier with ID: {}", id); 
     } 
  
     private void validateSupplierData(ApplicationCreateSupplierDTO dto) { 
         // Implement validation logic based on business rules 
         if (dto.getName() == null || dto.getName().isEmpty()) { 
             throw new IllegalArgumentException("Supplier name must be provided."); 
         } 
         // Additional validation rules 
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
         // Implement contact info validation logic 
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