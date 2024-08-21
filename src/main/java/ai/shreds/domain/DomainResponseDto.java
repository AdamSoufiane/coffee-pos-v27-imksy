import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomainResponseDto {
    private int statusCode;
    private String message;
    private UUID clientId;
    private UUID loyaltyProgramId;
}