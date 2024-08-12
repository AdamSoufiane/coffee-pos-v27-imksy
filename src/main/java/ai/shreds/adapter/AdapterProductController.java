package ai.shreds.adapter;

import ai.shreds.application.ApplicationProductServicePort;
import ai.shreds.shared.SharedRequestParams;
import ai.shreds.shared.SharedProductDTO;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class AdapterProductController {

    private final ApplicationProductServicePort productService;
    private static final Logger logger = LoggerFactory.getLogger(AdapterProductController.class);

    public AdapterProductController(ApplicationProductServicePort productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<AdapterCreateProductResponse> createProduct(@Valid @RequestBody AdapterCreateProductRequest request) {
        try {
            SharedRequestParams params = new SharedRequestParams(
                    request.getName(),
                    request.getDescription(),
                    request.getPrice(),
                    request.getCategoryId(),
                    request.getStockQuantity()
            );

            SharedProductDTO productDTO = productService.createProduct(params);

            AdapterCreateProductResponse response = new AdapterCreateProductResponse(
                    "success",
                    productDTO.getId(),
                    "Product created successfully"
            );

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            AdapterCreateProductResponse response = new AdapterCreateProductResponse(
                    "failure",
                    null,
                    "Invalid product data: " + e.getMessage()
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            AdapterCreateProductResponse response = new AdapterCreateProductResponse(
                    "failure",
                    null,
                    "Internal server error: " + e.getMessage()
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<AdapterCreateProductResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        AdapterCreateProductResponse response = new AdapterCreateProductResponse(
                "failure",
                null,
                "Invalid product data: " + e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AdapterCreateProductResponse> handleException(Exception e) {
        AdapterCreateProductResponse response = new AdapterCreateProductResponse(
                "failure",
                null,
                "Internal server error: " + e.getMessage()
        );
        logger.error("Exception occurred: ", e);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}