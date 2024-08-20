package ai.shreds.domain;

public class DomainSaveSupplierTransactionException extends RuntimeException {
    public DomainSaveSupplierTransactionException() {
        super();
    }

    public DomainSaveSupplierTransactionException(String message) {
        super(message);
    }

    public DomainSaveSupplierTransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainSaveSupplierTransactionException(Throwable cause) {
        super(cause);
    }
}