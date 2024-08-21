package ai.shreds.adapter; 
  
 import ai.shreds.shared.SharedUUID; 
 import ai.shreds.shared.SharedIntegerValueObject; 
 import ai.shreds.shared.SharedStringValueObject; 
 import ai.shreds.shared.SharedTimestamp; 
 import ai.shreds.shared.SharedInventoryUpdateEventApplicationDTO; 
 import lombok.NonNull; 
 import lombok.RequiredArgsConstructor; 
  
 /** 
  * Factory class to create instances of SharedInventoryUpdateEventApplicationDTO from AdapterInventoryUpdateRequestParam. 
  */ 
 @RequiredArgsConstructor 
 public class AdapterInventoryUpdateEventFactory { 
  
     /** 
      * Creates an instance of SharedInventoryUpdateEventApplicationDTO using the provided AdapterInventoryUpdateRequestParam. 
      * 
      * @param request the request parameter containing the data for creating the inventory update event 
      * @return a new instance of SharedInventoryUpdateEventApplicationDTO 
      * @throws IllegalArgumentException if any of the request parameters are null 
      */ 
     public SharedInventoryUpdateEventApplicationDTO createInventoryUpdateEvent(@NonNull AdapterInventoryUpdateRequestParam request) { 
         if (request.getId() == null || request.getProductId() == null || request.getQuantityChange() == null || request.getEventType() == null || request.getTimestamp() == null) { 
             throw new IllegalArgumentException("Request parameters cannot be null"); 
         } 
  
         SharedUUID id = new SharedUUID(request.getId().getUuid()); 
         SharedUUID productId = new SharedUUID(request.getProductId().getUuid()); 
         SharedIntegerValueObject quantityChange = new SharedIntegerValueObject(request.getQuantityChange()); 
         SharedStringValueObject eventType = new SharedStringValueObject(request.getEventType()); 
         SharedTimestamp timestamp = new SharedTimestamp(request.getTimestamp().getTimestamp()); 
  
         return new SharedInventoryUpdateEventApplicationDTO(id, productId, quantityChange, eventType, timestamp); 
     } 
 } 
 