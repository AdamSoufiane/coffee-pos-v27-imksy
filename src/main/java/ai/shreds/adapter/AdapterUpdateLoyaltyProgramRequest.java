import java.util.UUID;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdapterUpdateLoyaltyProgramRequest {
    private UUID clientId;
    private UUID loyaltyProgramId;
    private String programName;
    private String description;
    private String benefits;
    private LocalDateTime enrollmentDate;
}