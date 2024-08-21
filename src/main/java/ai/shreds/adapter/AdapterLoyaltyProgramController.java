package ai.shreds.adapter; 
  
 import ai.shreds.shared.AdapterEnrollClientRequest; 
 import ai.shreds.shared.AdapterEnrollClientResponse; 
 import ai.shreds.shared.AdapterUpdateLoyaltyProgramRequest; 
 import ai.shreds.shared.AdapterUpdateLoyaltyProgramResponse; 
 import ai.shreds.application.ApplicationEnrollClientInputPort; 
 import ai.shreds.application.ApplicationUpdateLoyaltyProgramInputPort; 
 import ai.shreds.adapter.mapper.AdapterLoyaltyProgramMapper; 
 import ai.shreds.adapter.exception.AdapterExceptionHandler; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.PostMapping; 
 import org.springframework.web.bind.annotation.PutMapping; 
 import org.springframework.web.bind.annotation.RequestBody; 
 import org.springframework.web.bind.annotation.RequestMapping; 
 import org.springframework.web.bind.annotation.RestController; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import javax.transaction.Transactional; 
 import javax.validation.Valid; 
 import org.springframework.web.bind.MethodArgumentNotValidException; 
  
 @RestController 
 @RequestMapping("/api/loyalty") 
 public class AdapterLoyaltyProgramController { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterLoyaltyProgramController.class); 
  
     @Autowired 
     private ApplicationEnrollClientInputPort enrollClientService; 
  
     @Autowired 
     private ApplicationUpdateLoyaltyProgramInputPort updateLoyaltyProgramService; 
  
     @Autowired 
     private AdapterLoyaltyProgramMapper mapper; 
  
     @Autowired 
     private AdapterExceptionHandler exceptionHandler; 
  
     @PostMapping("/enroll") 
     @Transactional 
     public ResponseEntity<AdapterEnrollClientResponse> enrollClient(@Valid @RequestBody AdapterEnrollClientRequest request) { 
         logger.info("Received enroll client request: {}", request); 
         try { 
             AdapterEnrollClientResponse response = enrollClientService.enrollClient(request); 
             logger.info("Enroll client response: {}", response); 
             return ResponseEntity.status(201).body(response); 
         } catch (MethodArgumentNotValidException e) { 
             return ResponseEntity.badRequest().body(null); 
         } catch (RuntimeException e) { 
             return exceptionHandler.handleException(e); 
         } 
     } 
  
     @PutMapping("/update") 
     @Transactional 
     public ResponseEntity<AdapterUpdateLoyaltyProgramResponse> updateLoyaltyProgram(@Valid @RequestBody AdapterUpdateLoyaltyProgramRequest request) { 
         logger.info("Received update loyalty program request: {}", request); 
         try { 
             AdapterUpdateLoyaltyProgramResponse response = updateLoyaltyProgramService.updateLoyaltyProgram(request); 
             logger.info("Update loyalty program response: {}", response); 
             return ResponseEntity.ok(response); 
         } catch (MethodArgumentNotValidException e) { 
             return ResponseEntity.badRequest().body(null); 
         } catch (RuntimeException e) { 
             return exceptionHandler.handleException(e); 
         } 
     } 
  
     @ExceptionHandler(Throwable.class) 
     public ResponseEntity<String> handleException(Throwable e) { 
         logger.error("Exception occurred: ", e); 
         return ResponseEntity.status(500).body(e.getMessage()); 
     } 
 } 
  
 // Add validation annotations to the request parameters in AdapterEnrollClientRequest and AdapterUpdateLoyaltyProgramRequest.