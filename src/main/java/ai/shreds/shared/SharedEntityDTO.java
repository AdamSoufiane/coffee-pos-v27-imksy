package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * SharedEntityDTO is a Data Transfer Object (DTO) that encapsulates data attributes
 * that are shared across different layers or components of the application.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedEntityDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The first attribute, representing a shared value.
     */
    private String attribute1;

    /**
     * The second attribute, representing another shared value.
     */
    private String attribute2;
}