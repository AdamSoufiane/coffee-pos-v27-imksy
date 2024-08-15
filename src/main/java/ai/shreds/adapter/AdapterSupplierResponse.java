package ai.shreds.adapter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Data Transfer Object for Supplier Response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdapterSupplierResponse {
    /**
     * Unique identifier for the supplier.
     */
    private Long id;
    /**
     * Name of the supplier.
     */
    private String name;
    /**
     * Phone number of the supplier.
     */
    private String contact_info_phone;
    /**
     * Email address of the supplier.
     */
    private String contact_info_email;
    /**
     * Physical address of the supplier.
     */
    private String address;
    /**
     * Postal code associated with the supplier's address.
     */
    private String zip_code;
    /**
     * City where the supplier is located.
     */
    private String city;
    /**
     * Registration code or reference code of the supplier.
     */
    private String rc;
    /**
     * Expiration or due date associated with the supplier's registration or contract.
     */
    private Date echeance_date;
    /**
     * Timestamp indicating when the supplier record was created.
     */
    private Timestamp created_at;
    /**
     * Timestamp indicating when the supplier record was last updated.
     */
    private Timestamp updated_at;
}