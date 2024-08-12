import java.math.BigDecimal;
import java.util.UUID;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdapterProductRequestDTO {
    @NotNull(message = "The field id cannot be null")
    private UUID id;

    @NotBlank(message = "The field name cannot be blank")
    @Size(max = 50, message = "The name should not exceed 50 characters")
    private String name;

    @Size(max = 255, message = "The description should not exceed 255 characters")
    private String description;

    @NotNull(message = "The price cannot be null")
    @DecimalMin(value = "0.0", message = "The price must be greater than or equal to 0")
    @Digits(integer = 10, fraction = 2, message = "The price can have a maximum of 10 digits and 2 decimal places")
    private BigDecimal price;

    @NotNull(message = "The category_id cannot be null")
    private UUID category_id;

    @NotNull(message = "The stock_quantity cannot be null")
    @Min(value = 0, message = "The stock_quantity must be greater than or equal to 0")
    private Integer stock_quantity;
}