package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainClientEntity; 
 import ai.shreds.domain.DomainAddressValue; 
 import ai.shreds.domain.DomainContactInfoValue; 
 import org.springframework.stereotype.Component; 
 import lombok.Getter; 
 import lombok.Setter; 
  
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
                 new DomainAddressValue(entity.getAddress(), entity.getZip_code(), entity.getCity()), 
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
         infrastructureEntity.setEmail(entity.getContact_info().getEmail()); 
         infrastructureEntity.setPhoneNumber(entity.getContact_info().getPhone()); 
         infrastructureEntity.setAddress(entity.getAddress().getAddress()); 
         infrastructureEntity.setZip_code(entity.getAddress().getZip_code()); 
         infrastructureEntity.setCity(entity.getAddress().getCity()); 
         infrastructureEntity.setRegistrationDate(entity.getRegistrationDate()); 
         return infrastructureEntity; 
     } 
 } 
  
 /** 
  * Entity class representing a client in the infrastructure layer. 
  */ 
 @Getter 
 @Setter 
 class InfrastructureClientEntity { 
     private UUID clientId; 
     private String firstName; 
     private String lastName; 
     private String email; 
     private String phoneNumber; 
     private String address; 
     private String zip_code; 
     private String city; 
     private DateTime registrationDate; 
 }