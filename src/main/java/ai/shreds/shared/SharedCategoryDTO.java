package ai.shreds.shared;

public class SharedCategoryDTO {
    private Long id;
    private String name;
    private Long parentId;

    // No-arg constructor
    public SharedCategoryDTO() {}

    // All-arg constructor
    public SharedCategoryDTO(Long id, String name, Long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getParentId() {
        return parentId;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}