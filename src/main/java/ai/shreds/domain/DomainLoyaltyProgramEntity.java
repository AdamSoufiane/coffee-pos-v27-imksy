package ai.shreds.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * DomainLoyaltyProgramEntity represents the loyalty program entity in the domain layer.
 * Note: Ensure the class adheres to the Hexagonal Architecture principles.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LoyaltyProgram")
public class DomainLoyaltyProgramEntity {
    @Id
    @Column(name = "loyaltyProgramId", nullable = false, unique = true)
    private UUID loyaltyProgramId;

    @Column(name = "programName", nullable = false)
    private String programName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "benefits", nullable = false)
    private String benefits;

    @Column(name = "enrollmentDate", nullable = false)
    private LocalDateTime enrollmentDate;

    @OneToMany(mappedBy = "loyaltyProgram", cascade = CascadeType.ALL)
    private List<DomainClientEntity> clients;
}