package shared;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Data
@Getter
@Setter
@Builder
public class ApplicationUpdateSupplierDTO {
    private String name;
    private String contact_info_phone;
    private String contact_info_email;
    private String address;
    private String zip_code;
    private String city;
    private String rc;
    private Date echeance_date;
}