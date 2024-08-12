package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedProductDTO {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private UUID categoryId;
    private int stockQuantity;
}