package ai.shreds.application; 
  
 import ai.shreds.adapter.AdapterUpdateLoyaltyProgramRequest; 
 import ai.shreds.adapter.AdapterUpdateLoyaltyProgramResponse; 
 import ai.shreds.adapter.AdapterLoyaltyProgramMapper; 
 import ai.shreds.domain.DomainLoyaltyProgramManagerService; 
 import ai.shreds.domain.DomainValidationException; 
 import ai.shreds.domain.DomainResponseDto; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import org.springframework.transaction.annotation.Transactional; 
 import lombok.RequiredArgsConstructor; 
 import lombok.extern.slf4j.Slf4j; 
  
 /** 
  * Service implementation for updating loyalty program details. 
  */ 
 @Slf4j 
 @Service 
 @RequiredArgsConstructor 
 public class ApplicationUpdateLoyaltyProgramService implements ApplicationUpdateLoyaltyProgramInputPort { 
  
     private final DomainLoyaltyProgramManagerService loyaltyProgramManagerService; 
     private final AdapterLoyaltyProgramMapper loyaltyProgramMapper; 
  
     /** 
      * Updates the details of an existing loyalty program for a client. 
      *  
      * @param request the request containing the updated loyalty program details 
      * @return the response with the status and updated details 
      */ 
     @Override 
     @Transactional 
     public AdapterUpdateLoyaltyProgramResponse updateLoyaltyProgram(AdapterUpdateLoyaltyProgramRequest request) { 
         try { 
             log.info("Updating loyalty program for client: {}", request.getClientId()); 
  
             // Validate input data 
             validateRequest(request); 
  
             // Map input request to domain entity 
             var loyaltyProgramEntity = loyaltyProgramMapper.mapToDomainEntity(request); 
  
             // Update loyalty program details 
             loyaltyProgramManagerService.updateLoyaltyProgram( 
                 request.getClientId(), 
                 request.getLoyaltyProgramId(), 
                 request.getProgramName(), 
                 request.getDescription(), 
                 request.getBenefits(), 
                 request.getEnrollmentDate() 
             ); 
  
             // Create domain response DTO 
             DomainResponseDto domainResponse = new DomainResponseDto(); 
             domainResponse.setStatusCode(200); 
             domainResponse.setMessage("Loyalty program updated successfully"); 
             domainResponse.setClientId(request.getClientId()); 
             domainResponse.setLoyaltyProgramId(request.getLoyaltyProgramId()); 
  
             // Map domain response to adapter response 
             return loyaltyProgramMapper.mapToAdapterResponse(domainResponse); 
         } catch (DomainValidationException e) { 
             log.error("Validation error: {}", e.getMessage()); 
             throw new ApplicationValidationException(e.getMessage()); 
         } catch (Exception e) { 
             log.error("Unexpected error: {}", e.getMessage()); 
             throw new ApplicationLoyaltyProgramException(e); 
         } 
     } 
  
     /** 
      * Validates the request to ensure all required fields are provided and valid. 
      *  
      * @param request the request to validate 
      * @throws DomainValidationException if any required field is missing or invalid 
      */ 
     private void validateRequest(AdapterUpdateLoyaltyProgramRequest request) throws DomainValidationException { 
         if (request.getClientId() == null || request.getLoyaltyProgramId() == null || 
             request.getProgramName() == null || request.getDescription() == null || 
             request.getBenefits() == null || request.getEnrollmentDate() == null) { 
             throw new DomainValidationException("All required fields must be provided and valid."); 
         } 
     } 
 } 
 