package ai.shreds.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import java.util.UUID;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import ai.shreds.domain.DomainLoyaltyProgramEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Client")
public class DomainClientEntity {

    @Id
    @Column(name = "clientId", nullable = false, unique = true)
    private UUID clientId;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Pattern(regexp = "^\+?[0-9. ()-]{7,25}$", message = "Invalid phone number")
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "zipCode", nullable = false)
    private String zipCode;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "registrationDate", nullable = false)
    private LocalDate registrationDate;

    @ManyToOne
    @JoinColumn(name = "loyaltyProgramId", nullable = false)
    private DomainLoyaltyProgramEntity loyaltyProgram;
}