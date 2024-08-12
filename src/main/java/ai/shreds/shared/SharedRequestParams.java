package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SharedRequestParams {
    @NotBlank
    private String name;

    @Size(max = 255)
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private UUID categoryId;

    @NotNull
    @Positive
    private Integer stock_quantity;
}