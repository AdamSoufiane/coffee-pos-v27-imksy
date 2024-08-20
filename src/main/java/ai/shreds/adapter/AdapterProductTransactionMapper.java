package ai.shreds.adapter;

import ai.shreds.domain.DomainProductTransaction;
import ai.shreds.adapter.AdapterProductTransactionRequest;

public class AdapterProductTransactionMapper {

    public DomainProductTransaction toDomain(AdapterProductTransactionRequest request) {
        if (request == null) {
            return null;
        }
        DomainProductTransaction domainProductTransaction = new DomainProductTransaction();
        domainProductTransaction.setProductId(request.getProductId());
        domainProductTransaction.setQuantity(request.getQuantity());
        domainProductTransaction.setPrice(request.getPrice());
        return domainProductTransaction;
    }
}