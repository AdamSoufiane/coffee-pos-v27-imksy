package ai.shreds.infrastructure;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

@Configuration
@Data
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class InfrastructureDataSource {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureDataSource.class);

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    public DataSource dataSource() {
        validateDataSourceProperties();
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driverClassName);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        logger.info("Configuring DataSource with URL: {}", jdbcUrl);
        return new HikariDataSource(config);
    }

    private void validateDataSourceProperties() {
        if (jdbcUrl == null || jdbcUrl.isEmpty()) {
            throw new IllegalArgumentException("JDBC URL must not be null or empty");
        }
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username must not be null or empty");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password must not be null or empty");
        }
        if (driverClassName == null || driverClassName.isEmpty()) {
            throw new IllegalArgumentException("Driver Class Name must not be null or empty");
        }
    }
}

package ai.shreds.adapter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

@ControllerAdvice
public class AdapterCategoryControllerException {

    private static final Logger logger = LoggerFactory.getLogger(AdapterCategoryControllerException.class);

    @ExceptionHandler(AdapterCategoryControllerException.NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(AdapterCategoryControllerException.NotFoundException ex) {
        logger.error("Category not found: ", ex);
        return new ResponseEntity<>("{\"error\": \"Category not found.\"}", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleDataAccessException(DataAccessException ex) {
        logger.error("Database error: ", ex);
        return new ResponseEntity<>("{\"error\": \"Database error occurred.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleInternalServerError(Exception ex) {
        logger.error("Internal server error: ", ex);
        return new ResponseEntity<>("{\"error\": \"Internal server error.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
            super(message);
        }
    }
}

package ai.shreds.application;

import ai.shreds.domain.DomainCategoryServicePort;
import ai.shreds.shared.SharedCategoryDTO;
import ai.shreds.shared.SharedCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.dao.DataAccessException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class ApplicationCategoryServiceImpl implements ApplicationCategoryServicePort {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationCategoryServiceImpl.class);

    private final DomainCategoryServicePort domainService;
    private final SharedCategoryMapper sharedCategoryMapper;

    public ApplicationCategoryServiceImpl(DomainCategoryServicePort domainService, SharedCategoryMapper sharedCategoryMapper) {
        this.domainService = domainService;
        this.sharedCategoryMapper = sharedCategoryMapper;
    }

    @Override
    public SharedCategoryDTO getCategoryDetails(Long id) {
        try {
            return sharedCategoryMapper.toSharedCategoryDTO(domainService.getCategoryDetails(id));
        } catch (DataAccessException e) {
            logger.error("Error fetching category details for id: {}", id, e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with ID " + id + " not found", e);
        }
    }

    @Override
    public List<SharedCategoryDTO> getAllCategories() {
        try {
            return domainService.getAllCategories().stream()
                    .map(sharedCategoryMapper::toSharedCategoryDTO)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            logger.error("Error fetching all categories", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while fetching all categories", e);
        }
    }

    @Override
    public List<SharedCategoryDTO> getSubcategories(Long parentId) {
        try {
            return domainService.getSubcategories(parentId).stream()
                    .map(sharedCategoryMapper::toSharedCategoryDTO)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            logger.error("Error fetching subcategories for parent id: {}", parentId, e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subcategories for parent ID " + parentId + " not found", e);
        }
    }
}

package ai.shreds.infrastructure;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

/**
 * Exception handler for data access exceptions in the Category repository.
 */
@RestControllerAdvice
public class InfrastructureCategoryRepositoryException {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureCategoryRepositoryException.class);

    /**
     * Handles DataAccessException and returns an appropriate error response.
     * @param ex the DataAccessException
     * @return the error response
     */
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleDataAccessException(DataAccessException ex) {
        logger.error("A database error occurred: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("INTERNAL_SERVER_ERROR", "A database error occurred: " + ex.getMessage()));
    }

    /**
     * Error response class to structure error messages.
     */
    public static class ErrorResponse {
        private String errorCode;
        private String errorMessage;

        /**
         * Constructor with error code and message.
         * @param errorCode the error code
         * @param errorMessage the error message
         */
        public ErrorResponse(String errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}

package ai.shreds.infrastructure;

public class DataAccessException extends RuntimeException {
    private final String errorCode;

    public DataAccessException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}