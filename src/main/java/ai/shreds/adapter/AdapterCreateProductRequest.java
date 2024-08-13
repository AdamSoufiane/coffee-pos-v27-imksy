package ai.shreds.adapter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class AdapterCreateProductRequest {
    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    @Size(min = 1, max = 255)
    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    private UUID category_id;

    @NotNull
    private Integer stock_quantity;
}