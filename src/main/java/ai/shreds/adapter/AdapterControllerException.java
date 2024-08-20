package ai.shreds.adapter; 
  
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ControllerAdvice; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.context.request.WebRequest; 
 import org.springframework.web.bind.MethodArgumentNotValidException; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @ControllerAdvice 
 public class AdapterControllerException { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterControllerException.class); 
  
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<String> handleException(Exception e, WebRequest request) { 
         logger.error("Exception occurred: ", e); 
         return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
  
     @ExceptionHandler(IllegalArgumentException.class) 
     public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e, WebRequest request) { 
         logger.error("IllegalArgumentException occurred: ", e); 
         return new ResponseEntity<>("Invalid input: " + e.getMessage(), HttpStatus.BAD_REQUEST); 
     } 
  
     @ExceptionHandler(NullPointerException.class) 
     public ResponseEntity<String> handleNullPointerException(NullPointerException e, WebRequest request) { 
         logger.error("NullPointerException occurred: ", e); 
         return new ResponseEntity<>("A required value was null: " + e.getMessage(), HttpStatus.BAD_REQUEST); 
     } 
  
     @ExceptionHandler(ApplicationStoreSupplierTransactionException.class) 
     public ResponseEntity<String> handleApplicationStoreSupplierTransactionException(ApplicationStoreSupplierTransactionException e, WebRequest request) { 
         logger.error("ApplicationStoreSupplierTransactionException occurred: ", e); 
         return new ResponseEntity<>("Error in storing supplier transaction: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
  
     @ExceptionHandler(MethodArgumentNotValidException.class) 
     public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) { 
         logger.error("Validation failed: ", e); 
         return new ResponseEntity<>("Validation error: " + e.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST); 
     } 
 } 
 