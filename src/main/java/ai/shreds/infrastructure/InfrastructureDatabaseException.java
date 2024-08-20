package ai.shreds.infrastructure;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.lang.Throwable;

@NoArgsConstructor
@AllArgsConstructor
public class InfrastructureDatabaseException extends Exception {
    private static final long serialVersionUID = 1L;

    public InfrastructureDatabaseException(String message) {
        super(message);
    }

    public InfrastructureDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public InfrastructureDatabaseException(Throwable cause) {
        super(cause);
    }
}