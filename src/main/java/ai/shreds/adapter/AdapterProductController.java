package ai.shreds.adapter; 
  
 import ai.shreds.application.ApplicationProductServicePort; 
 import ai.shreds.shared.SharedRequestParams; 
 import ai.shreds.shared.SharedProductDTO; 
 import lombok.Getter; 
 import lombok.Setter; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.PostMapping; 
 import org.springframework.web.bind.annotation.RequestBody; 
 import org.springframework.web.bind.annotation.RequestMapping; 
 import org.springframework.web.bind.annotation.RestController; 
  
 import javax.validation.Valid; 
 import javax.validation.constraints.Min; 
 import javax.validation.constraints.NotBlank; 
 import javax.validation.constraints.NotNull; 
 import java.util.UUID; 
  
 @RestController 
 @RequestMapping("/api/products") 
 public class AdapterProductController { 
  
     private final ApplicationProductServicePort productService; 
     private static final Logger logger = LoggerFactory.getLogger(AdapterProductController.class); 
  
     public AdapterProductController(ApplicationProductServicePort productService) { 
         this.productService = productService; 
     } 
  
     @PostMapping 
     public ResponseEntity<AdapterCreateProductResponse> createProduct(@Valid @RequestBody AdapterCreateProductRequest request) { 
         try { 
             SharedRequestParams params = new SharedRequestParams( 
                     request.getName(), 
                     request.getDescription(), 
                     request.getPrice(), 
                     request.getCategoryId(), 
                     request.getStockQuantity() 
             ); 
  
             SharedProductDTO productDTO = productService.createProduct(params); 
  
             AdapterCreateProductResponse response = new AdapterCreateProductResponse( 
                     "success", 
                     productDTO.getId(), 
                     "Product created successfully" 
             ); 
  
             return new ResponseEntity<>(response, HttpStatus.CREATED); 
         } catch (IllegalArgumentException e) { 
             AdapterCreateProductResponse response = new AdapterCreateProductResponse( 
                     "failure", 
                     null, 
                     "Invalid product data: " + e.getMessage() 
             ); 
             return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
         } catch (Exception e) { 
             AdapterCreateProductResponse response = new AdapterCreateProductResponse( 
                     "failure", 
                     null, 
                     "Internal server error: " + e.getMessage() 
             ); 
             return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
         } 
     } 
  
     @ExceptionHandler(Exception.class) 
     public void handleException(Exception e) { 
         logger.error("Exception occurred: ", e); 
     } 
 } 
  
 @Getter 
 @Setter 
 class AdapterCreateProductRequest { 
     @NotBlank(message = "Name is mandatory") 
     private String name; 
  
     private String description; 
  
     @NotNull(message = "Price is mandatory") 
     @Min(value = 0, message = "Price must be a positive value") 
     private double price; 
  
     @NotNull(message = "Category ID is mandatory") 
     private UUID categoryId; 
  
     @NotNull(message = "Stock quantity is mandatory") 
     @Min(value = 0, message = "Stock quantity must be a non-negative integer") 
     private int stockQuantity; 
 } 
  
 @Getter 
 @Setter 
 class AdapterCreateProductResponse { 
     private String status; 
     private UUID productId; 
     private String message; 
  
     public AdapterCreateProductResponse(String status, UUID productId, String message) { 
         this.status = status; 
         this.productId = productId; 
         this.message = message; 
     } 
 } 
 