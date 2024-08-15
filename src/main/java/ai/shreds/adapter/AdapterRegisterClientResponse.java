import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdapterRegisterClientResponse {
    private UUID clientId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private AdapterAddress address;
    private LocalDateTime registrationDate;
}