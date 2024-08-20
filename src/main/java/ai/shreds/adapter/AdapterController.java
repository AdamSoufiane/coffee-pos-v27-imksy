package ai.shreds.adapter;

import ai.shreds.application.ApplicationStoreSupplierTransactionInputPort;
import ai.shreds.adapter.AdapterResponseDTO;
import ai.shreds.adapter.AdapterRequestParams;
import ai.shreds.adapter.AdapterSupplierTransactionMapper;
import ai.shreds.domain.DomainSupplierTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.UUID;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * AdapterController handles HTTP requests related to supplier transactions.
 */
@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class AdapterController {

    private final ApplicationStoreSupplierTransactionInputPort applicationStoreSupplierTransactionInputPort;
    private final AdapterSupplierTransactionMapper adapterSupplierTransactionMapper;

    /**
     * Stores supplier transaction details.
     *
     * @param params the transaction details
     * @return the response entity containing the stored transaction details
     */
    @PostMapping
    public ResponseEntity<AdapterResponseDTO> storeSupplierTransaction(@Valid @RequestBody AdapterRequestParams params) {
        AdapterResponseDTO responseDTO = applicationStoreSupplierTransactionInputPort.storeSupplierTransaction(params);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Handles generic exceptions.
     *
     * @param e the exception
     * @return the response entity containing the error message
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}