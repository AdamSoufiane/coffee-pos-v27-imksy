package ai.shreds.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationSupplierDTO {
    private Long id;
    private String name;
    private String contact_info_phone;
    private String contact_info_email;
    private String address;
    private String zip_code;
    private String city;
    private String rc;
    private Date echeance_date;
    private Timestamp created_at;
    private Timestamp updated_at;
}