package ai.shreds.adapter;

import ai.shreds.domain.DomainSupplierTransaction;
import ai.shreds.domain.DomainProductTransaction;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AdapterSupplierTransactionMapper {

    private static final Logger logger = LoggerFactory.getLogger(AdapterSupplierTransactionMapper.class);

    public DomainSupplierTransaction toDomain(AdapterRequestParams request) {
        Assert.notNull(request, "Request cannot be null");
        Assert.notNull(request.getSupplierId(), "Supplier ID cannot be null");
        Assert.notNull(request.getTransactionDate(), "Transaction date cannot be null");
        Assert.notNull(request.getProducts(), "Products list cannot be null");

        List<DomainProductTransaction> productTransactions = request.getProducts().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());

        logger.info("Mapping AdapterRequestParams to DomainSupplierTransaction");

        return DomainSupplierTransaction.builder()
                .id(UUID.randomUUID())
                .supplierId(request.getSupplierId())
                .transactionDate(request.getTransactionDate())
                .products(productTransactions)
                .build();
    }

    private DomainProductTransaction toDomain(AdapterProductTransactionRequest request) {
        Assert.notNull(request, "Request cannot be null");
        Assert.notNull(request.getProductId(), "Product ID cannot be null");
        Assert.notNull(request.getQuantity(), "Quantity cannot be null");
        Assert.notNull(request.getPrice(), "Price cannot be null");

        logger.info("Mapping AdapterProductTransactionRequest to DomainProductTransaction");

        return DomainProductTransaction.builder()
                .id(UUID.randomUUID())
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .build();
    }
}