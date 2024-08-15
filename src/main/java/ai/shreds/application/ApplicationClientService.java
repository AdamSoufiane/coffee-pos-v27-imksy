package ai.shreds.application;

import ai.shreds.domain.DomainClientService;
import ai.shreds.domain.DomainClientEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationClientService implements ApplicationClientInputPort {
    private final DomainClientService domainService;
    private static final Logger logger = LoggerFactory.getLogger(ApplicationClientService.class);

    /**
     * Registers a new client by validating and processing the provided client data.
     * The client details are saved using the domain service.
     *
     * @param dto the client data transfer object containing client details
     * @return the registered client details as a new data transfer object
     */
    @Override
    public ApplicationClientDTO registerClient(ApplicationClientDTO dto) {
        try {
            // Validate client data
            logger.info("Validating client data...");
            domainService.validateClientData(dto);

            // Process registration
            logger.info("Processing client registration...");
            domainService.processRegistration(dto);

            // Save client details
            logger.info("Saving client details...");
            DomainClientEntity registeredEntity = domainService.saveClient(dto);

            // Return the registered client details
            logger.info("Client registration successful.");
            return registeredEntity.toDTO();
        } catch (Exception e) {
            logger.error("Error during client registration: ", e);
            throw new RuntimeException("Client registration failed", e);
        }
    }
}