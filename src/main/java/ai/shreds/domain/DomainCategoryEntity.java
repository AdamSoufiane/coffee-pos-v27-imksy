package ai.shreds.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Category")
public class DomainCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    // Self-referencing relationship to represent the hierarchy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    private DomainCategoryEntity parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DomainCategoryEntity> children = new ArrayList<>();

    public DomainCategoryEntity() {}

    public DomainCategoryEntity(Long id, String name, Long parentId, DomainCategoryEntity parent, List<DomainCategoryEntity> children) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.parent = parent;
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public DomainCategoryEntity getParent() {
        return parent;
    }

    public void setParent(DomainCategoryEntity parent) {
        this.parent = parent;
    }

    public List<DomainCategoryEntity> getChildren() {
        return children;
    }

    public void setChildren(List<DomainCategoryEntity> children) {
        this.children = children;
    }
}