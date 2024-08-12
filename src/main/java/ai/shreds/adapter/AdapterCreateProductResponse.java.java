package ai.shreds.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdapterCreateProductResponse {
    private String status;
    private UUID productId;
    private String message;
}