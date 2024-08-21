package ai.shreds.domain;

import ai.shreds.shared.DomainClientEntity;
import ai.shreds.shared.DomainLoyaltyProgramEntity;
import ai.shreds.application.DomainValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import java.util.UUID;
import java.time.LocalDateTime;

@Slf4j
@Service
public class DomainLoyaltyProgramManagerService {

    private final DomainClientRepositoryPort clientRepository;
    private final DomainLoyaltyProgramRepositoryPort loyaltyProgramRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public DomainLoyaltyProgramManagerService(DomainClientRepositoryPort clientRepository, DomainLoyaltyProgramRepositoryPort loyaltyProgramRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.clientRepository = clientRepository;
        this.loyaltyProgramRepository = loyaltyProgramRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public void enrollClient(UUID clientId, UUID loyaltyProgramId) throws DomainValidationException {
        log.info("Enrolling client {} in loyalty program {}", clientId, loyaltyProgramId);
        if (clientId == null || loyaltyProgramId == null) {
            throw new DomainValidationException("Client ID and Loyalty Program ID cannot be null");
        }

        DomainClientEntity client = clientRepository.findById(clientId);
        if (client == null) {
            log.error("Client not found with ID: {}", clientId);
            throw new DomainValidationException("Client not found");
        }

        DomainLoyaltyProgramEntity loyaltyProgram = loyaltyProgramRepository.findById(loyaltyProgramId);
        if (loyaltyProgram == null) {
            log.error("Loyalty Program not found with ID: {}", loyaltyProgramId);
            throw new DomainValidationException("Loyalty Program not found");
        }

        if (client.getLoyaltyProgramId() != null) {
            log.error("Client {} is already enrolled in a loyalty program", clientId);
            throw new DomainValidationException("Client is already enrolled in a loyalty program");
        }

        client.setLoyaltyProgramId(loyaltyProgramId);
        clientRepository.save(client);

        try {
            kafkaTemplate.send("ClientUpdates", "Client " + clientId + " enrolled in loyalty program " + loyaltyProgramId);
        } catch (Exception e) {
            log.error("Failed to send Kafka message for client enrollment", e);
        }
        log.info("Client {} enrolled in loyalty program {} successfully", clientId, loyaltyProgramId);
    }

    @Transactional
    public void updateLoyaltyProgram(UUID clientId, UUID loyaltyProgramId, String programName, String description, String benefits, LocalDateTime enrollmentDate) throws DomainValidationException {
        log.info("Updating loyalty program {} for client {}", loyaltyProgramId, clientId);
        if (clientId == null || loyaltyProgramId == null || programName == null || description == null || benefits == null || enrollmentDate == null) {
            throw new DomainValidationException("Input parameters cannot be null");
        }

        if (enrollmentDate.isAfter(LocalDateTime.now())) {
            throw new DomainValidationException("Enrollment date cannot be in the future");
        }

        DomainClientEntity client = clientRepository.findById(clientId);
        if (client == null) {
            log.error("Client not found with ID: {}", clientId);
            throw new DomainValidationException("Client not found");
        }

        DomainLoyaltyProgramEntity loyaltyProgram = loyaltyProgramRepository.findById(loyaltyProgramId);
        if (loyaltyProgram == null) {
            log.error("Loyalty Program not found with ID: {}", loyaltyProgramId);
            throw new DomainValidationException("Loyalty Program not found");
        }

        loyaltyProgram.setProgramName(programName);
        loyaltyProgram.setDescription(description);
        loyaltyProgram.setBenefits(benefits);
        loyaltyProgram.setEnrollmentDate(enrollmentDate);
        loyaltyProgramRepository.save(loyaltyProgram);

        try {
            kafkaTemplate.send("ClientUpdates", "Client " + clientId + " updated loyalty program " + loyaltyProgramId);
        } catch (Exception e) {
            log.error("Failed to send Kafka message for loyalty program update", e);
        }
        log.info("Loyalty program {} for client {} updated successfully", loyaltyProgramId, clientId);
    }
}