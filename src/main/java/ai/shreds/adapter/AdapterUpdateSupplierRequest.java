package ai.shreds.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdapterUpdateSupplierRequest {
    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Size(max = 20)
    private String contact_info_phone;

    @NotBlank
    @Email
    @Size(max = 255)
    private String contact_info_email;

    @NotBlank
    @Size(max = 255)
    private String address;

    @NotBlank
    @Size(max = 10)
    private String zip_code;

    @NotBlank
    @Size(max = 100)
    private String city;

    @NotBlank
    @Size(max = 50)
    private String rc;

    @NotNull
    private Date echeance_date;
}