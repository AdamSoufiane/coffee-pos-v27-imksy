package ai.shreds.application;

import ai.shreds.adapter.AdapterRequestParams;
import ai.shreds.adapter.AdapterResponseDTO;
import ai.shreds.adapter.AdapterSupplierTransactionMapper;
import ai.shreds.domain.DomainSaveSupplierTransactionPort;
import ai.shreds.domain.DomainCalculateTotalAmountPort;
import ai.shreds.domain.DomainSupplierTransaction;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ai.shreds.application.ApplicationSendInventoryUpdateNotificationOutputPort;
import ai.shreds.application.ApplicationStoreSupplierTransactionInputPort;
import ai.shreds.application.ApplicationStoreSupplierTransactionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

/**
 * Service class for storing supplier transactions.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationStoreSupplierTransactionService implements ApplicationStoreSupplierTransactionInputPort {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStoreSupplierTransactionService.class);

    private final DomainSaveSupplierTransactionPort domainSaveSupplierTransactionPort;
    private final DomainCalculateTotalAmountPort domainCalculateTotalAmountPort;
    private final ApplicationSendInventoryUpdateNotificationOutputPort applicationSendInventoryUpdateNotificationOutputPort;
    private final AdapterSupplierTransactionMapper adapterSupplierTransactionMapper;

    /**
     * Stores a supplier transaction.
     *
     * @param params the transaction details
     * @return the stored transaction details
     */
    @Override
    public AdapterResponseDTO storeSupplierTransaction(AdapterRequestParams params) {
        try {
            log.info("Starting to store supplier transaction");

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

            log.info("Successfully stored supplier transaction");

            // Return response DTO
            return responseDTO;
        } catch (Exception e) {
            log.error("Error storing supplier transaction", e);
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
        }).collect(Collectors.toList()));
        return responseDTO;
    }
}