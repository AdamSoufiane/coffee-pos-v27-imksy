package ai.shreds.infrastructure;

import ai.shreds.shared.AdapterProductResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.KafkaException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ControllerAdvice
@Slf4j
public class InfrastructureException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AdapterProductResponseDTO> handleException(Exception exception) {
        // Log the exception details
        log.error("Exception occurred: ", exception);

        // Create a response object with error details
        AdapterProductResponseDTO response = new AdapterProductResponseDTO();
        response.setStatus("failure");
        response.setProductId(null); // No product ID in case of an error

        // Return the response entity with HTTP status 500 (Internal Server Error)
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<AdapterProductResponseDTO> handleDataAccessException(DataAccessException exception) {
        // Log the exception details
        log.error("Data access exception occurred: ", exception);

        // Create a response object with error details
        AdapterProductResponseDTO response = new AdapterProductResponseDTO();
        response.setStatus("failure");
        response.setProductId(null); // No product ID in case of an error

        // Return the response entity with HTTP status 500 (Internal Server Error)
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(KafkaException.class)
    public ResponseEntity<AdapterProductResponseDTO> handleKafkaException(KafkaException exception) {
        // Log the exception details
        log.error("Kafka exception occurred: ", exception);

        // Create a response object with error details
        AdapterProductResponseDTO response = new AdapterProductResponseDTO();
        response.setStatus("failure");
        response.setProductId(null); // No product ID in case of an error

        // Return the response entity with HTTP status 500 (Internal Server Error)
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AdapterProductResponseDTO> handleValidationException(MethodArgumentNotValidException exception) {
        // Log the exception details
        log.error("Validation exception occurred: ", exception);

        // Create a response object with error details
        AdapterProductResponseDTO response = new AdapterProductResponseDTO();
        response.setStatus("failure");
        response.setProductId(null); // No product ID in case of an error

        // Return the response entity with HTTP status 400 (Bad Request)
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}