package shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdapterEnrollClientResponse {
    private int statusCode;
    private String message;
    private UUID clientId;
    private UUID loyaltyProgramId;
}