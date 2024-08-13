package ai.shreds.adapter;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class AdapterUpdateProductRequest {
    @NotNull
    private UUID id;

    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @Size(max = 1024)
    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    private UUID category_id;

    @NotNull
    private Integer stock_quantity;
}