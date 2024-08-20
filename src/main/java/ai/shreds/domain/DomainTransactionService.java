package ai.shreds.domain; 
  
 import ai.shreds.domain.exception.DomainSaveSupplierTransactionException; 
 import ai.shreds.domain.exception.DomainCalculateTotalAmountException; 
 import ai.shreds.domain.model.DomainSupplierTransaction; 
 import ai.shreds.domain.model.DomainProductTransaction; 
 import ai.shreds.domain.port.DomainSaveSupplierTransactionPort; 
 import ai.shreds.domain.port.DomainCalculateTotalAmountPort; 
 import ai.shreds.domain.repository.DomainRepository; 
 import lombok.AllArgsConstructor; 
 import lombok.NoArgsConstructor; 
 import lombok.Data; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import org.springframework.transaction.annotation.Transactional; 
  
 import java.math.BigDecimal; 
  
 /** 
  * Service class for handling supplier transactions. 
  * Implements DomainSaveSupplierTransactionPort and DomainCalculateTotalAmountPort. 
  */ 
 @Service 
 @Data 
 @AllArgsConstructor 
 @NoArgsConstructor 
 public class DomainTransactionService implements DomainSaveSupplierTransactionPort, DomainCalculateTotalAmountPort { 
  
     private DomainRepository domainRepository; 
  
     /** 
      * Saves a supplier transaction entity to the database. 
      * @param supplierTransaction The supplier transaction entity to be saved. 
      * @throws DomainSaveSupplierTransactionException if an error occurs while saving the transaction. 
      */ 
     @Override 
     @Transactional 
     public void saveSupplierTransaction(DomainSupplierTransaction supplierTransaction) { 
         try { 
             domainRepository.save(supplierTransaction); 
         } catch (Exception e) { 
             throw new DomainSaveSupplierTransactionException("Failed to save supplier transaction", e); 
         } 
     } 
  
     /** 
      * Calculates the total amount of a supplier transaction. 
      * @param supplierTransaction The supplier transaction entity whose total amount is to be calculated. 
      * @return The calculated total amount. 
      * @throws DomainCalculateTotalAmountException if an error occurs while calculating the total amount. 
      */ 
     @Override 
     public BigDecimal calculateTotalAmount(DomainSupplierTransaction supplierTransaction) { 
         try { 
             BigDecimal totalAmount = BigDecimal.ZERO; 
             for (DomainProductTransaction productTransaction : supplierTransaction.getProducts()) { 
                 BigDecimal productTotal = productTransaction.getPrice().multiply(BigDecimal.valueOf(productTransaction.getQuantity())); 
                 totalAmount = totalAmount.add(productTotal); 
             } 
             return totalAmount; 
         } catch (Exception e) { 
             throw new DomainCalculateTotalAmountException("Failed to calculate total amount", e); 
         } 
     } 
 } 
 