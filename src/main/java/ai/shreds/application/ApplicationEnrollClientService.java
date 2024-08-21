package ai.shreds.application;

import ai.shreds.adapter.AdapterEnrollClientRequest;
import ai.shreds.adapter.AdapterEnrollClientResponse;
import ai.shreds.adapter.AdapterLoyaltyProgramMapper;
import ai.shreds.adapter.AdapterKafkaProducer;
import ai.shreds.application.exceptions.ApplicationValidationException;
import ai.shreds.domain.DomainClientEntity;
import ai.shreds.domain.DomainLoyaltyProgramManagerService;
import ai.shreds.domain.DomainValidationException;
import ai.shreds.domain.DomainClientRepositoryPort;
import ai.shreds.domain.DomainResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ApplicationEnrollClientService implements ApplicationEnrollClientInputPort {

    private final DomainLoyaltyProgramManagerService loyaltyProgramManagerService;
    private final AdapterLoyaltyProgramMapper loyaltyProgramMapper;
    private final AdapterKafkaProducer kafkaProducer;
    private final DomainClientRepositoryPort clientRepository;

    @Autowired
    public ApplicationEnrollClientService(DomainLoyaltyProgramManagerService loyaltyProgramManagerService, AdapterLoyaltyProgramMapper loyaltyProgramMapper, AdapterKafkaProducer kafkaProducer, DomainClientRepositoryPort clientRepository) {
        this.loyaltyProgramManagerService = loyaltyProgramManagerService;
        this.loyaltyProgramMapper = loyaltyProgramMapper;
        this.kafkaProducer = kafkaProducer;
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public AdapterEnrollClientResponse enrollClient(AdapterEnrollClientRequest request) {
        log.info("Enrolling client with ID: {}", request.getClientId());

        // Validate input data
        validateRequest(request);

        // Check if client exists
        DomainClientEntity clientEntity = clientRepository.findById(request.getClientId()).orElseThrow(() -> new ApplicationValidationException("Client does not exist."));

        // Map request to domain entity
        DomainClientEntity mappedClientEntity = loyaltyProgramMapper.mapToDomainEntity(request);

        // Enroll client in loyalty program
        try {
            loyaltyProgramManagerService.enrollClient(mappedClientEntity.getClientId(), mappedClientEntity.getLoyaltyProgramId());
        } catch (DomainValidationException e) {
            log.error("Validation error during client enrollment: {}", e.getMessage());
            throw new ApplicationValidationException(e.getMessage());
        }

        // Produce Kafka message
        kafkaProducer.produceMessage("ClientUpdates", "Client enrolled in loyalty program");

        // Map result to adapter response
        return loyaltyProgramMapper.mapToAdapterResponse(new DomainResponseDto(201, "Client enrolled in loyalty program successfully", clientEntity.getClientId(), clientEntity.getLoyaltyProgramId()));
    }

    private void validateRequest(AdapterEnrollClientRequest request) {
        if (request.getClientId() == null || request.getProgramName() == null || request.getDescription() == null || request.getBenefits() == null || request.getEnrollmentDate() == null) {
            throw new ApplicationValidationException("All required fields must be provided and valid.");
        }
        if (request.getEnrollmentDate().isAfter(LocalDateTime.now())) {
            throw new ApplicationValidationException("The enrollment date must not be in the future.");
        }
    }
}