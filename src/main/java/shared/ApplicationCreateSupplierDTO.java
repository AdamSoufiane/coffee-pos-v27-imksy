package shared;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationCreateSupplierDTO {
    private String name;
    private String contact_info_phone;
    private String contact_info_email;
    private String address;
    private String zip_code;
    private String city;
    private String rc;
    private Date echeance_date;
}