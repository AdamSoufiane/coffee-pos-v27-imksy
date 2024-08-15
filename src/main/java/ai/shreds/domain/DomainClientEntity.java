package ai.shreds.domain; 
  
 import ai.shreds.application.ApplicationClientDTO; 
 import lombok.AllArgsConstructor; 
  
 import java.time.LocalDateTime; 
 import java.util.UUID; 
  
 /** 
  * Represents a client entity in the domain layer. 
  */ 
 @AllArgsConstructor 
 public class DomainClientEntity { 
  
     /** 
      * The unique identifier for the client. 
      */ 
     private final UUID clientId; 
  
     /** 
      * The first name of the client. 
      */ 
     private final String firstName; 
  
     /** 
      * The last name of the client. 
      */ 
     private final String lastName; 
  
     /** 
      * The contact information of the client. 
      */ 
     private final DomainContactInfoValue contact_info; 
  
     /** 
      * The address of the client. 
      */ 
     private final DomainAddressValue address; 
  
     /** 
      * The registration date of the client. 
      */ 
     private final LocalDateTime registrationDate; 
  
     /** 
      * Converts this domain entity to an ApplicationClientDTO. 
      * 
      * @return the corresponding ApplicationClientDTO 
      */ 
     public ApplicationClientDTO toDTO() { 
         ApplicationClientDTO dto = new ApplicationClientDTO(); 
         dto.setClientId(this.clientId); 
         dto.setFirstName(this.firstName); 
         dto.setLastName(this.lastName); 
         dto.setContact_info(this.contact_info.toDTO()); 
         dto.setAddress(this.address.toDTO()); 
         dto.setRegistrationDate(this.registrationDate); 
         return dto; 
     } 
  
     public UUID getClientId() { 
         return clientId; 
     } 
  
     public String getFirstName() { 
         return firstName; 
     } 
  
     public String getLastName() { 
         return lastName; 
     } 
  
     public DomainContactInfoValue getContact_info() { 
         return contact_info; 
     } 
  
     public DomainAddressValue getAddress() { 
         return address; 
     } 
  
     public LocalDateTime getRegistrationDate() { 
         return registrationDate; 
     } 
  
     /** 
      * Constructs a DomainClientEntity with validation. 
      * 
      * @param clientId the unique identifier for the client 
      * @param firstName the first name of the client 
      * @param lastName the last name of the client 
      * @param contact_info the contact information of the client 
      * @param address the address of the client 
      * @param registrationDate the registration date of the client 
      */ 
     public DomainClientEntity(UUID clientId, String firstName, String lastName, DomainContactInfoValue contact_info, DomainAddressValue address, LocalDateTime registrationDate) { 
         if (clientId == null || firstName == null || lastName == null || contact_info == null || address == null || registrationDate == null) { 
             throw new IllegalArgumentException("All fields must be provided and non-null"); 
         } 
         this.clientId = clientId; 
         this.firstName = firstName; 
         this.lastName = lastName; 
         this.contact_info = contact_info; 
         this.address = address; 
         this.registrationDate = registrationDate; 
     } 
 } 
 