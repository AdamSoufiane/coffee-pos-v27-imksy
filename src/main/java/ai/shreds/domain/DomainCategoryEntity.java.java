package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * Represents a category in the hierarchical structure.
 */
@Entity
@Table(name = "Category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DomainCategoryEntity {

    /**
     * Unique identifier for the category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the category.
     */
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    /**
     * Identifier of the parent category. Nullable to allow root categories.
     */
    @Column(name = "parent_id")
    private Long parentId;
}