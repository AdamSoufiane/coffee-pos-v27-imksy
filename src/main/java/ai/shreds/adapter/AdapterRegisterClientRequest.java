package shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdapterRegisterClientRequest {
    @NotNull(message = "First name cannot be null")
    @Size(min = 2, max = 50, message = "First name length should be between 2 and 50")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, max = 50, message = "Last name length should be between 2 and 50")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email length should be maximum 100")
    private String email;

    @NotNull(message = "Phone number cannot be null")
    @Size(min = 10, max = 15, message = "Phone number length should be between 10 and 15")
    private String phoneNumber;

    @NotNull(message = "Contact info cannot be null")
    private AdapterContactInfo contact_info;

    @NotNull(message = "Address cannot be null")
    private AdapterAddress address;
}