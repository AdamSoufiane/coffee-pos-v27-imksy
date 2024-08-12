package ai.shreds.adapter;

import ai.shreds.application.ApplicationUpdateProductInputPort;
import ai.shreds.shared.AdapterProductRequestDTO;
import ai.shreds.shared.AdapterProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class AdapterProductController {

    private final ApplicationUpdateProductInputPort applicationUpdateProductInputPort;
    private final AdapterProductMapper adapterProductMapper;
    private static final Logger logger = LoggerFactory.getLogger(AdapterProductController.class);

    /**
     * Handles the request to update product details.
     * @param request The product details to be updated.
     * @return The response indicating the status of the update operation.
     */
    @PutMapping("/update")
    public ResponseEntity<AdapterProductResponseDTO> handleRequest(@Valid @RequestBody AdapterProductRequestDTO request) {
        try {
            validateRequest(request);
            DomainProductEntity domainProductEntity = adapterProductMapper.toDomain(request);
            AdapterProductResponseDTO response = applicationUpdateProductInputPort.updateProduct(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(createErrorResponse("Invalid product data: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Error updating product: ", e);
            return new ResponseEntity<>(createErrorResponse("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Exception handler for general exceptions.
     * @param exception The caught exception.
     * @return The response indicating the failure.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AdapterProductResponseDTO> handleException(Exception exception) {
        logger.error("Unhandled exception: ", exception);
        return new ResponseEntity<>(createErrorResponse("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Validates the request body fields.
     * @param request The product details to be validated.
     */
    private void validateRequest(AdapterProductRequestDTO request) {
        if (request.getPrice() != null && request.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be a positive value");
        }
        if (request.getStock_quantity() != null && request.getStock_quantity() < 0) {
            throw new IllegalArgumentException("Stock quantity must be a non-negative integer");
        }
    }

    /**
     * Creates an error response DTO.
     * @param message The error message.
     * @return The error response DTO.
     */
    private AdapterProductResponseDTO createErrorResponse(String message) {
        AdapterProductResponseDTO response = new AdapterProductResponseDTO();
        response.setStatus("failure");
        response.setProduct_id(null);
        response.setMessage(message);
        return response;
    }
}