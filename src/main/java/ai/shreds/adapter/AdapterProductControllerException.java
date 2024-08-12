package ai.shreds.adapter;

import ai.shreds.shared.AdapterProductResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class AdapterProductControllerException {

    @ExceptionHandler({Exception.class, ConstraintViolationException.class, NoSuchElementException.class})
    public AdapterProductResponseDTO handleException(Exception exception) {
        log.error("Exception occurred: ", exception);
        String message = exception.getMessage() != null ? exception.getMessage() : "An unexpected error occurred.";
        return AdapterProductResponseDTO.builder()
                .status("failure")
                .product_id(null)
                .message(message)
                .build();
    }
}