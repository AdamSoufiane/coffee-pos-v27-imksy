package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedUUID; 
 import ai.shreds.shared.SharedStringValueObject; 
 import ai.shreds.shared.SharedIntegerValueObject; 
 import ai.shreds.shared.SharedTimestamp; 
 import lombok.Value; 
 import lombok.Builder; 
  
 /** 
  * DomainProductDomainDTO is a Data Transfer Object (DTO) that represents the product entity in the domain layer. 
  * This class encapsulates the product's data and provides a structured way to transfer this data between different layers of the application. 
  */ 
 @Value 
 @Builder 
 public class DomainProductDomainDTO { 
     SharedUUID id; 
     SharedStringValueObject name; 
     SharedIntegerValueObject currentQuantity; 
     SharedTimestamp lastUpdated; 
 } 
 