package ai.shreds.adapter; 
  
 import ai.shreds.domain.DomainClientEntity; 
 import ai.shreds.domain.DomainLoyaltyProgramEntity; 
 import ai.shreds.shared.AdapterEnrollClientRequest; 
 import ai.shreds.shared.AdapterUpdateLoyaltyProgramRequest; 
 import ai.shreds.shared.AdapterEnrollClientResponse; 
 import ai.shreds.shared.AdapterUpdateLoyaltyProgramResponse; 
 import ai.shreds.domain.DomainResponseDto; 
 import org.springframework.util.StringUtils; 
  
 public class AdapterLoyaltyProgramMapper { 
  
     public DomainClientEntity mapToDomainEntity(AdapterEnrollClientRequest request) { 
         if (request == null || request.getClientId() == null || !StringUtils.hasText(request.getProgramName())) { 
             throw new IllegalArgumentException("Invalid input data"); 
         } 
         return DomainClientEntity.builder() 
                 .clientId(request.getClientId()) 
                 .firstName(request.getFirstName()) 
                 .lastName(request.getLastName()) 
                 .email(request.getEmail()) 
                 .phoneNumber(request.getPhoneNumber()) 
                 .address(request.getAddress()) 
                 .zipCode(request.getZipCode()) 
                 .city(request.getCity()) 
                 .registrationDate(request.getRegistrationDate()) 
                 .loyaltyProgramId(request.getLoyaltyProgramId()) 
                 .build(); 
     } 
  
     public DomainLoyaltyProgramEntity mapToDomainEntity(AdapterUpdateLoyaltyProgramRequest request) { 
         if (request == null || request.getLoyaltyProgramId() == null || !StringUtils.hasText(request.getProgramName())) { 
             throw new IllegalArgumentException("Invalid input data"); 
         } 
         return DomainLoyaltyProgramEntity.builder() 
                 .loyaltyProgramId(request.getLoyaltyProgramId()) 
                 .programName(request.getProgramName()) 
                 .description(request.getDescription()) 
                 .benefits(request.getBenefits()) 
                 .enrollmentDate(request.getEnrollmentDate()) 
                 .build(); 
     } 
  
     public AdapterEnrollClientResponse mapToAdapterResponse(DomainResponseDto response) { 
         if (response == null || response.getClientId() == null || response.getLoyaltyProgramId() == null) { 
             throw new IllegalArgumentException("Invalid response data"); 
         } 
         return AdapterEnrollClientResponse.builder() 
                 .statusCode(response.getStatusCode()) 
                 .message(response.getMessage()) 
                 .clientId(response.getClientId()) 
                 .loyaltyProgramId(response.getLoyaltyProgramId()) 
                 .build(); 
     } 
  
     public AdapterUpdateLoyaltyProgramResponse mapToAdapterResponse(DomainResponseDto response) { 
         if (response == null || response.getClientId() == null || response.getLoyaltyProgramId() == null) { 
             throw new IllegalArgumentException("Invalid response data"); 
         } 
         return AdapterUpdateLoyaltyProgramResponse.builder() 
                 .statusCode(response.getStatusCode()) 
                 .message(response.getMessage()) 
                 .clientId(response.getClientId()) 
                 .loyaltyProgramId(response.getLoyaltyProgramId()) 
                 .build(); 
     } 
 } 
 