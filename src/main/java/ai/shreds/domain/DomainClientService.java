package ai.shreds.domain;

import ai.shreds.application.ApplicationClientDTO;
import ai.shreds.domain.ports.DomainClientRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.regex.Pattern;

@Service
@Validated
public class DomainClientService {

    private static final Logger logger = LoggerFactory.getLogger(DomainClientService.class);
    private final DomainClientRepositoryPort domainClientRepositoryPort;
    private final KafkaTemplate<String, ApplicationClientDTO> kafkaTemplate;

    @Autowired
    public DomainClientService(DomainClientRepositoryPort domainClientRepositoryPort, KafkaTemplate<String, ApplicationClientDTO> kafkaTemplate) {
        this.domainClientRepositoryPort = domainClientRepositoryPort;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void validateClientData(@NotNull ApplicationClientDTO dto) {
        logger.info("Validating client data");
        validateFields(dto);
    }

    private void validateFields(@NotNull ApplicationClientDTO dto) {
        validateFirstName(dto);
        validateLastName(dto);
        validateEmailAddress(dto);
        validatePhoneNumber(dto);
        validateAddress(dto);
    }

    private void validateFirstName(@NotNull ApplicationClientDTO dto) {
        if (dto.getFirstName() == null || dto.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name is required.");
        }
    }

    private void validateLastName(@NotNull ApplicationClientDTO dto) {
        if (dto.getLastName() == null || dto.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name is required.");
        }
    }

    private void validateEmailAddress(@NotNull ApplicationClientDTO dto) {
        if (dto.getContact_info() == null || dto.getContact_info().getEmail() == null || dto.getContact_info().getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required.");
        }
        if (!isValidEmail(dto.getContact_info().getEmail())) {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

    private void validatePhoneNumber(@NotNull ApplicationClientDTO dto) {
        if (dto.getContact_info() == null || dto.getContact_info().getPhone() == null || dto.getContact_info().getPhone().isEmpty()) {
            throw new IllegalArgumentException("Phone number is required.");
        }
        if (!isValidPhoneNumber(dto.getContact_info().getPhone())) {
            throw new IllegalArgumentException("Invalid phone number format.");
        }
    }

    private void validateAddress(@NotNull ApplicationClientDTO dto) {
        if (dto.getAddress() == null || dto.getAddress().getAddress() == null || dto.getAddress().getAddress().isEmpty()) {
            throw new IllegalArgumentException("Address is required.");
        }
        if (dto.getAddress() == null || dto.getAddress().getZip_code() == null || dto.getAddress().getZip_code().isEmpty()) {
            throw new IllegalArgumentException("Zip code is required.");
        }
        if (dto.getAddress() == null || dto.getAddress().getCity() == null || dto.getAddress().getCity().isEmpty()) {
            throw new IllegalArgumentException("City is required.");
        }
    }

    @Transactional
    public void processRegistration(@NotNull ApplicationClientDTO dto) {
        logger.info("Processing client registration");
        DomainClientEntity clientEntity = new DomainClientEntity(
                dto.getClientId(),
                dto.getFirstName(),
                dto.getLastName(),
                new DomainContactInfoValue(dto.getContact_info().getPhone(), dto.getContact_info().getEmail()),
                new DomainAddressValue(dto.getAddress().getAddress(), dto.getAddress().getZip_code(), dto.getAddress().getCity()),
                dto.getRegistrationDate()
        );
        try {
            domainClientRepositoryPort.save(clientEntity);
            logger.info("Client entity saved to database");
            kafkaTemplate.send("ClientUpdates", dto).addCallback(
                result -> logger.info("Kafka message produced for client registration"),
                ex -> logger.error("Failed to produce Kafka message", ex)
            );
        } catch (Exception e) {
            logger.error("Failed to process client registration", e);
            // Handle failure
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^\\+?[0-9. ()-]{7,25}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        return pattern.matcher(phoneNumber).matches();
    }
}