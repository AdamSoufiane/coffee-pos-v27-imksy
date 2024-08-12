package ai.shreds.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdapterCategoryRequestParams {
    @NotNull(message = "Id cannot be null")
    private Long id;

    private Long parentId;
}