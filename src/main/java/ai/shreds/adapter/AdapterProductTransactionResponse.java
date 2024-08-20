package ai.shreds.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdapterProductTransactionResponse {
    private UUID productId;
    private int quantity;
    private BigDecimal price;
}