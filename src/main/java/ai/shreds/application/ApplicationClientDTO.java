package ai.shreds.application;

import ai.shreds.shared.AdapterAddress;
import ai.shreds.shared.AdapterContactInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationClientDTO {
    @NotNull
    @Size(max=36)
    @JsonProperty("clientId")
    private UUID clientId;

    @NotNull
    @Size(max=100)
    @JsonProperty("firstName")
    private String firstName;

    @NotNull
    @Size(max=100)
    @JsonProperty("lastName")
    private String lastName;

    @NotNull
    @JsonProperty("contact_info")
    private AdapterContactInfo contact_info;

    @NotNull
    @JsonProperty("address")
    private AdapterAddress address;

    @NotNull
    @JsonProperty("registrationDate")
    private LocalDateTime registrationDate;
}