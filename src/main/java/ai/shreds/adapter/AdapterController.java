package ai.shreds.adapter; 
  
 import ai.shreds.application.ApplicationStoreSupplierTransactionInputPort; 
 import ai.shreds.domain.DomainSupplierTransaction; 
 import ai.shreds.domain.DomainProductTransaction; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.PostMapping; 
 import org.springframework.web.bind.annotation.RequestBody; 
 import org.springframework.web.bind.annotation.RequestMapping; 
 import org.springframework.web.bind.annotation.RestController; 
 import lombok.RequiredArgsConstructor; 
 import lombok.Data; 
 import javax.validation.Valid; 
 import javax.validation.constraints.NotNull; 
 import javax.validation.constraints.Size; 
 import java.util.UUID; 
 import java.util.List; 
 import java.util.stream.Collectors; 
  
 /** 
  * AdapterController handles HTTP requests related to supplier transactions. 
  */ 
 @RestController 
 @RequestMapping("/api/transactions") 
 @RequiredArgsConstructor 
 public class AdapterController { 
  
     private final ApplicationStoreSupplierTransactionInputPort applicationStoreSupplierTransactionInputPort; 
     private final AdapterSupplierTransactionMapper adapterSupplierTransactionMapper; 
     private final AdapterProductTransactionMapper adapterProductTransactionMapper; 
  
     /** 
      * Stores supplier transaction details. 
      *  
      * @param params the transaction details 
      * @return the response entity containing the stored transaction details 
      */ 
     @PostMapping 
     public ResponseEntity<AdapterResponseDTO> storeSupplierTransaction(@Valid @RequestBody AdapterRequestParams params) { 
         DomainSupplierTransaction domainSupplierTransaction = adapterSupplierTransactionMapper.toDomain(params); 
         AdapterResponseDTO responseDTO = applicationStoreSupplierTransactionInputPort.storeSupplierTransaction(params); 
         return new ResponseEntity<>(responseDTO, HttpStatus.OK); 
     } 
  
     /** 
      * Handles custom exceptions. 
      *  
      * @param e the custom exception 
      * @return the response entity containing the error message 
      */ 
     @ExceptionHandler(CustomException.class) 
     public ResponseEntity<String> handleCustomException(CustomException e) { 
         return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); 
     } 
  
     /** 
      * Handles generic exceptions. 
      *  
      * @param e the exception 
      * @return the response entity containing the error message 
      */ 
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<String> handleException(Exception e) { 
         return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
 } 
  
 /** 
  * AdapterRequestParams represents the request parameters for storing a supplier transaction. 
  */ 
 @Data 
 class AdapterRequestParams { 
     @NotNull 
     private UUID supplierId; 
     @NotNull 
     private String transactionDate; 
     @NotNull 
     @Size(min = 1) 
     private List<AdapterProductTransactionRequest> products; 
 } 
  
 /** 
  * AdapterProductTransactionRequest represents the request parameters for a product transaction. 
  */ 
 @Data 
 class AdapterProductTransactionRequest { 
     @NotNull 
     private UUID productId; 
     @NotNull 
     private int quantity; 
     @NotNull 
     private double price; 
 } 
  
 /** 
  * AdapterResponseDTO represents the response data transfer object for a supplier transaction. 
  */ 
 @Data 
 class AdapterResponseDTO { 
     private UUID id; 
     private UUID supplierId; 
     private String transactionDate; 
     private double totalAmount; 
     private List<AdapterProductTransactionResponse> products; 
 } 
  
 /** 
  * AdapterProductTransactionResponse represents the response data transfer object for a product transaction. 
  */ 
 @Data 
 class AdapterProductTransactionResponse { 
     private UUID productId; 
     private int quantity; 
     private double price; 
 } 
  
 /** 
  * AdapterSupplierTransactionMapper maps AdapterRequestParams to DomainSupplierTransaction. 
  */ 
 class AdapterSupplierTransactionMapper { 
     public DomainSupplierTransaction toDomain(AdapterRequestParams request) { 
         if (request == null) throw new CustomException("Request cannot be null"); 
         DomainSupplierTransaction domainSupplierTransaction = new DomainSupplierTransaction(); 
         domainSupplierTransaction.setSupplierId(request.getSupplierId()); 
         domainSupplierTransaction.setTransactionDate(request.getTransactionDate()); 
         domainSupplierTransaction.setProducts(adapterProductTransactionMapper.toDomain(request.getProducts())); 
         return domainSupplierTransaction; 
     } 
 } 
  
 /** 
  * AdapterProductTransactionMapper maps AdapterProductTransactionRequest to DomainProductTransaction. 
  */ 
 class AdapterProductTransactionMapper { 
     public List<DomainProductTransaction> toDomain(List<AdapterProductTransactionRequest> requests) { 
         if (requests == null) throw new CustomException("Product transactions cannot be null"); 
         return requests.stream().map(this::toDomain).collect(Collectors.toList()); 
     } 
  
     public DomainProductTransaction toDomain(AdapterProductTransactionRequest request) { 
         if (request == null) throw new CustomException("Product transaction request cannot be null"); 
         DomainProductTransaction domainProductTransaction = new DomainProductTransaction(); 
         domainProductTransaction.setProductId(request.getProductId()); 
         domainProductTransaction.setQuantity(request.getQuantity()); 
         domainProductTransaction.setPrice(request.getPrice()); 
         return domainProductTransaction; 
     } 
 } 
  
 /** 
  * CustomException is thrown when a custom error occurs. 
  */ 
 class CustomException extends RuntimeException { 
     public CustomException(String message) { 
         super(message); 
     } 
 }