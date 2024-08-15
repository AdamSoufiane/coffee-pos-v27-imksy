package ai.shreds.domain;

import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * DomainContactInfoValue is a value object representing the contact information of a client.
 * It ensures that the phone number and email address are valid according to the specified business rules.
 */
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class DomainContactInfoValue {

    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number format")
    private final String phone;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email address format")
    private final String email;
}