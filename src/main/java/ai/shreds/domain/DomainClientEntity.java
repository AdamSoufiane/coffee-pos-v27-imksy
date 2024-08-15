package ai.shreds.domain;

import ai.shreds.application.ApplicationClientDTO;
import ai.shreds.domain.valueobjects.DomainAddressValue;
import ai.shreds.domain.valueobjects.DomainContactInfoValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a client entity in the domain layer.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DomainClientEntity {

    /**
     * The unique identifier for the client.
     */
    private UUID clientId;

    /**
     * The first name of the client.
     */
    private String firstName;

    /**
     * The last name of the client.
     */
    private String lastName;

    /**
     * The contact information of the client.
     */
    private DomainContactInfoValue contact_info;

    /**
     * The address of the client.
     */
    private DomainAddressValue address;

    /**
     * The registration date of the client.
     */
    private LocalDateTime registrationDate;

    /**
     * Converts this domain entity to an ApplicationClientDTO.
     *
     * @return the corresponding ApplicationClientDTO
     */
    public ApplicationClientDTO toDTO() {
        return new ApplicationClientDTO(
            this.clientId,
            this.firstName,
            this.lastName,
            this.contact_info.toDTO(),
            this.address.toDTO(),
            this.registrationDate
        );
    }
}