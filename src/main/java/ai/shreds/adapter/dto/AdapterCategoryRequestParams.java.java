package ai.shreds.adapter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data transfer object for category request parameters.
 */
public class AdapterCategoryRequestParams {
    /**
     * Name of the category.
     */
    @JsonProperty("name")
    private String name;

    /**
     * Identifier of the parent category (optional).
     */
    @JsonProperty("parentId")
    private Long parentId;

    // No-arg constructor
    public AdapterCategoryRequestParams() {}

    // All-arg constructor
    public AdapterCategoryRequestParams(String name, Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}