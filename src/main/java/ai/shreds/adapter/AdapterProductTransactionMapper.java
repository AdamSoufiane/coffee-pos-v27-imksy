package ai.shreds.adapter;

import ai.shreds.domain.DomainProductTransaction;
import lombok.NonNull;

public class AdapterProductTransactionMapper {

    public DomainProductTransaction toDomain(@NonNull AdapterProductTransactionRequest request) {
        DomainProductTransaction domainProductTransaction = new DomainProductTransaction();
        domainProductTransaction.setProductId(request.getProductId());
        domainProductTransaction.setQuantity(request.getQuantity());
        domainProductTransaction.setPrice(request.getPrice());
        return domainProductTransaction;
    }
}