package ai.shreds.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Data Transfer Object for supplier request parameters.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdapterRequestParams {
    /**
     * Name of the supplier.
     */
    @NotEmpty(message = "Name is mandatory")
    @Size(max = 255)
    private String name;

    /**
     * Phone number of the supplier.
     */
    @NotEmpty(message = "Phone number is mandatory")
    @Size(max = 20)
    private String contact_info_phone;

    /**
     * Email address of the supplier.
     */
    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Size(max = 255)
    private String contact_info_email;

    /**
     * Physical address of the supplier.
     */
    @NotEmpty(message = "Address is mandatory")
    @Size(max = 255)
    private String address;

    /**
     * Postal code associated with the supplier's address.
     */
    @NotEmpty(message = "Zip code is mandatory")
    @Size(max = 10)
    private String zip_code;

    /**
     * City where the supplier is located.
     */
    @NotEmpty(message = "City is mandatory")
    @Size(max = 100)
    private String city;

    /**
     * Registration code or reference code of the supplier.
     */
    @NotEmpty(message = "Registration code is mandatory")
    @Size(max = 50)
    private String rc;

    /**
     * Expiration or due date associated with the supplier's registration or contract.
     */
    @NotNull(message = "Echeance date is mandatory")
    @Future(message = "Echeance date must be a future date")
    private Date echeance_date;
}