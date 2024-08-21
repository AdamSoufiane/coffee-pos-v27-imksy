package shared;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdapterEnrollClientRequest {
    @NotNull
    private UUID clientId;

    @NotEmpty
    @Size(max = 100)
    private String programName;

    @Size(max = 255)
    private String description;

    @Size(max = 255)
    private String benefits;

    @NotNull
    private LocalDateTime enrollmentDate;
}