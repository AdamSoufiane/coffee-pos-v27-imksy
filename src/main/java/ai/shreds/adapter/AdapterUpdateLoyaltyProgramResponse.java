import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

/**
 * AdapterUpdateLoyaltyProgramResponse represents the response for updating a loyalty program.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdapterUpdateLoyaltyProgramResponse {
    /**
     * The HTTP status code for the response.
     */
    private int statusCode;
    
    /**
     * The message associated with the response.
     */
    private String message;
    
    /**
     * The client identifier associated with the loyalty program update.
     */
    private UUID clientId;
    
    /**
     * The loyalty program identifier that was updated.
     */
    private UUID loyaltyProgramId;
}