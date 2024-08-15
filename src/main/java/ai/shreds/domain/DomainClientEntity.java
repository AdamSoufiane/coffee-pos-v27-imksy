package ai.shreds.domain;

import ai.shreds.application.ApplicationClientDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import shared.AdapterContactInfo;
import shared.AdapterAddress;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a client entity in the domain layer.
 */
@Getter
@AllArgsConstructor
public class DomainClientEntity {

    /**
     * The unique identifier for the client.
     */
    private final UUID clientId;

    /**
     * The first name of the client.
     */
    private final String firstName;

    /**
     * The last name of the client.
     */
    private final String lastName;

    /**
     * The contact information of the client.
     */
    private final DomainContactInfoValue contactInfo;

    /**
     * The address of the client.
     */
    private final DomainAddressValue address;

    /**
     * The registration date of the client.
     */
    private final LocalDateTime registrationDate;

    /**
     * Converts this domain entity to an ApplicationClientDTO.
     *
     * @return the corresponding ApplicationClientDTO
     */
    public ApplicationClientDTO toDTO() {
        ApplicationClientDTO dto = new ApplicationClientDTO();
        dto.setClientId(this.clientId);
        dto.setFirstName(this.firstName);
        dto.setLastName(this.lastName);
        dto.setContactInfo(new AdapterContactInfo(this.contactInfo.getPhone(), this.contactInfo.getEmail()));
        dto.setAddress(new AdapterAddress(this.address.getAddress(), this.address.getZip_code(), this.address.getCity()));
        dto.setRegistrationDate(this.registrationDate);
        return dto;
    }
}