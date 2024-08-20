package ai.shreds.adapter;

import ai.shreds.domain.DomainSupplierTransaction;
import ai.shreds.domain.DomainProductTransaction;
import ai.shreds.adapter.AdapterRequestParams;
import ai.shreds.adapter.AdapterProductTransactionRequest;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class AdapterSupplierTransactionMapper {

    public DomainSupplierTransaction toDomain(AdapterRequestParams request) {
        if (request == null || request.getSupplierId() == null || request.getTransactionDate() == null || request.getProducts() == null) {
            throw new IllegalArgumentException("Invalid request parameters");
        }

        List<DomainProductTransaction> productTransactions = request.getProducts().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());

        log.info("Mapping AdapterRequestParams to DomainSupplierTransaction");

        UUID supplierTransactionId = UUID.randomUUID(); // Generating a unique ID

        return DomainSupplierTransaction.builder()
                .id(supplierTransactionId)
                .supplierId(request.getSupplierId())
                .transactionDate(request.getTransactionDate())
                .products(productTransactions)
                .build();
    }

    private DomainProductTransaction toDomain(AdapterProductTransactionRequest request) {
        if (request == null || request.getProductId() == null || request.getQuantity() == null || request.getPrice() == null) {
            throw new IllegalArgumentException("Invalid product transaction request parameters");
        }

        log.info("Mapping AdapterProductTransactionRequest to DomainProductTransaction");

        UUID productTransactionId = UUID.randomUUID(); // Generating a unique ID

        return DomainProductTransaction.builder()
                .id(productTransactionId)
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .build();
    }
}