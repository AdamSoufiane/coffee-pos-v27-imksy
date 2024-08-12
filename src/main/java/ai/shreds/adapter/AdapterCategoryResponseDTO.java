package ai.shreds.adapter;

import lombok.Data;

@Data
public class AdapterCategoryResponseDTO {
    private Long id;
    private String name;
    private Long parentId;
}