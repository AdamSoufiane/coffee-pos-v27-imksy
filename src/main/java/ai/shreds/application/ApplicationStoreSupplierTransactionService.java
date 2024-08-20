package ai.shreds.application; 
  
 import ai.shreds.adapter.AdapterRequestParams; 
 import ai.shreds.adapter.AdapterResponseDTO; 
 import ai.shreds.adapter.AdapterSupplierTransactionMapper; 
 import ai.shreds.domain.DomainSaveSupplierTransactionPort; 
 import ai.shreds.domain.DomainCalculateTotalAmountPort; 
 import ai.shreds.domain.DomainSupplierTransaction; 
 import lombok.RequiredArgsConstructor; 
 import lombok.Getter; 
 import lombok.Setter; 
 import lombok.AllArgsConstructor; 
 import org.springframework.stereotype.Service; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.util.Assert; 
  
 /** 
  * Service class for storing supplier transactions. 
  */ 
 @Service 
 @RequiredArgsConstructor 
 public class ApplicationStoreSupplierTransactionService implements ApplicationStoreSupplierTransactionInputPort { 
  
     private static final Logger logger = LoggerFactory.getLogger(ApplicationStoreSupplierTransactionService.class); 
  
     private final DomainSaveSupplierTransactionPort domainSaveSupplierTransactionPort; 
     private final DomainCalculateTotalAmountPort domainCalculateTotalAmountPort; 
     private final ApplicationSendInventoryUpdateNotificationOutputPort applicationSendInventoryUpdateNotificationOutputPort; 
     private final AdapterSupplierTransactionMapper adapterSupplierTransactionMapper; 
  
     /** 
      * Constructor with null checks for dependencies. 
      */ 
     public ApplicationStoreSupplierTransactionService(DomainSaveSupplierTransactionPort domainSaveSupplierTransactionPort, 
                                                        DomainCalculateTotalAmountPort domainCalculateTotalAmountPort, 
                                                        ApplicationSendInventoryUpdateNotificationOutputPort applicationSendInventoryUpdateNotificationOutputPort, 
                                                        AdapterSupplierTransactionMapper adapterSupplierTransactionMapper) { 
         Assert.notNull(domainSaveSupplierTransactionPort, "DomainSaveSupplierTransactionPort must not be null"); 
         Assert.notNull(domainCalculateTotalAmountPort, "DomainCalculateTotalAmountPort must not be null"); 
         Assert.notNull(applicationSendInventoryUpdateNotificationOutputPort, "ApplicationSendInventoryUpdateNotificationOutputPort must not be null"); 
         Assert.notNull(adapterSupplierTransactionMapper, "AdapterSupplierTransactionMapper must not be null"); 
         this.domainSaveSupplierTransactionPort = domainSaveSupplierTransactionPort; 
         this.domainCalculateTotalAmountPort = domainCalculateTotalAmountPort; 
         this.applicationSendInventoryUpdateNotificationOutputPort = applicationSendInventoryUpdateNotificationOutputPort; 
         this.adapterSupplierTransactionMapper = adapterSupplierTransactionMapper; 
     } 
  
     /** 
      * Stores a supplier transaction. 
      *  
      * @param params the transaction details 
      * @return the stored transaction details 
      */ 
     @Override 
     public AdapterResponseDTO storeSupplierTransaction(AdapterRequestParams params) { 
         try { 
             logger.info("Starting to store supplier transaction"); 
  
             // Convert AdapterRequestParams to DomainSupplierTransaction 
             DomainSupplierTransaction domainSupplierTransaction = adapterSupplierTransactionMapper.toDomain(params); 
  
             // Validate input data 
             validateTransaction(domainSupplierTransaction); 
  
             // Calculate total amount 
             domainSupplierTransaction.setTotalAmount(domainCalculateTotalAmountPort.calculateTotalAmount(domainSupplierTransaction)); 
  
             // Save SupplierTransaction 
             domainSaveSupplierTransactionPort.saveSupplierTransaction(domainSupplierTransaction); 
  
             // Send Kafka notification 
             AdapterResponseDTO responseDTO = toResponseDTO(domainSupplierTransaction); 
             applicationSendInventoryUpdateNotificationOutputPort.sendInventoryUpdateNotification(responseDTO); 
  
             logger.info("Successfully stored supplier transaction"); 
  
             // Return response DTO 
             return responseDTO; 
         } catch (Exception e) { 
             logger.error("Error storing supplier transaction", e); 
             throw new ApplicationStoreSupplierTransactionException("Error storing supplier transaction: " + e.getMessage(), e); 
         } 
     } 
  
     /** 
      * Validates the transaction details. 
      *  
      * @param transaction the transaction to validate 
      */ 
     private void validateTransaction(DomainSupplierTransaction transaction) { 
         if (transaction.getProducts() == null || transaction.getProducts().isEmpty()) { 
             throw new ApplicationStoreSupplierTransactionException("Each SupplierTransaction must have at least one ProductTransaction."); 
         } 
         if (transaction.getSupplierId() == null || transaction.getTransactionDate() == null) { 
             throw new ApplicationStoreSupplierTransactionException("A valid SupplierTransaction must include a supplier_id and transaction_date."); 
         } 
         transaction.getProducts().forEach(product -> { 
             if (product.getProductId() == null || product.getQuantity() <= 0 || product.getPrice() == null) { 
                 throw new ApplicationStoreSupplierTransactionException("Invalid product details in the transaction."); 
             } 
         }); 
     } 
  
     /** 
      * Converts DomainSupplierTransaction to AdapterResponseDTO. 
      *  
      * @param domainSupplierTransaction the domain supplier transaction 
      * @return the adapter response DTO 
      */ 
     private AdapterResponseDTO toResponseDTO(DomainSupplierTransaction domainSupplierTransaction) { 
         AdapterResponseDTO responseDTO = new AdapterResponseDTO(); 
         responseDTO.setId(domainSupplierTransaction.getId()); 
         responseDTO.setSupplierId(domainSupplierTransaction.getSupplierId()); 
         responseDTO.setTransactionDate(domainSupplierTransaction.getTransactionDate()); 
         responseDTO.setTotalAmount(domainSupplierTransaction.getTotalAmount()); 
         responseDTO.setProducts(domainSupplierTransaction.getProducts().stream().map(product -> { 
             AdapterResponseDTO.AdapterProductTransactionResponse productResponse = new AdapterResponseDTO.AdapterProductTransactionResponse(); 
             productResponse.setProductId(product.getProductId()); 
             productResponse.setQuantity(product.getQuantity()); 
             productResponse.setPrice(product.getPrice()); 
             return productResponse; 
         }).toList()); 
         return responseDTO; 
     } 
 }