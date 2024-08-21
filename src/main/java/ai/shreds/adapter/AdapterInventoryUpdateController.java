package ai.shreds.adapter; 
  
 import ai.shreds.application.ApplicationInventoryUpdateServicePort; 
 import ai.shreds.shared.SharedUUID; 
 import ai.shreds.shared.SharedTimestamp; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.PostMapping; 
 import org.springframework.web.bind.annotation.RequestBody; 
 import org.springframework.web.bind.annotation.RequestMapping; 
 import org.springframework.web.bind.annotation.RestController; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @RestController 
 @RequestMapping("/inventory/update") 
 public class AdapterInventoryUpdateController { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterInventoryUpdateController.class); 
  
     @Autowired 
     private ApplicationInventoryUpdateServicePort inventoryUpdateService; 
  
     @Autowired 
     private AdapterInventoryUpdateEventFactory eventFactory; 
  
     @PostMapping("/sales") 
     public ResponseEntity<AdapterInventoryUpdateResponseDTO> updateInventoryForSale(@RequestBody AdapterInventoryUpdateRequestParam params) { 
         try { 
             if (params == null || params.getId() == null || params.getProductId() == null || params.getQuantityChange() == null || params.getEventType() == null) { 
                 return ResponseEntity.badRequest().body(null); 
             } 
             SharedInventoryUpdateEventApplicationDTO event = eventFactory.createInventoryUpdateEvent(params); 
             SharedInventoryUpdateResponseApplicationDTO response = inventoryUpdateService.updateInventoryForSale(event); 
             AdapterInventoryUpdateResponseDTO responseDTO = new AdapterInventoryUpdateResponseDTO( 
                     response.getProductId(), 
                     response.getUpdatedQuantity().getValue(), 
                     response.getLastUpdated() 
             ); 
             logger.info("Inventory updated for sale: {}", responseDTO); 
             return ResponseEntity.ok(responseDTO); 
         } catch (Exception e) { 
             return handleException(e); 
         } 
     } 
  
     @PostMapping("/restock") 
     public ResponseEntity<AdapterInventoryUpdateResponseDTO> updateInventoryForRestock(@RequestBody AdapterInventoryUpdateRequestParam params) { 
         try { 
             if (params == null || params.getId() == null || params.getProductId() == null || params.getQuantityChange() == null || params.getEventType() == null) { 
                 return ResponseEntity.badRequest().body(null); 
             } 
             SharedInventoryUpdateEventApplicationDTO event = eventFactory.createInventoryUpdateEvent(params); 
             SharedInventoryUpdateResponseApplicationDTO response = inventoryUpdateService.updateInventoryForRestock(event); 
             AdapterInventoryUpdateResponseDTO responseDTO = new AdapterInventoryUpdateResponseDTO( 
                     response.getProductId(), 
                     response.getUpdatedQuantity().getValue(), 
                     response.getLastUpdated() 
             ); 
             logger.info("Inventory updated for restock: {}", responseDTO); 
             return ResponseEntity.ok(responseDTO); 
         } catch (Exception e) { 
             return handleException(e); 
         } 
     } 
  
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<AdapterInventoryUpdateResponseDTO> handleException(Exception e) { 
         logger.error("Exception occurred: ", e); 
         AdapterInventoryUpdateResponseDTO errorResponse = new AdapterInventoryUpdateResponseDTO(null, null, null); 
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse); 
     } 
 } 
 