package ai.shreds.application;

import shared.AdapterContactInfo;
import shared.AdapterAddress;
import java.util.UUID;
import java.time.LocalDateTime;

/**
 * Interface for client registration in the application layer.
 */
public interface ApplicationClientInputPort {
    /**
     * Registers a new client.
     *
     * @param dto the client data transfer object
     * @return the registered client data transfer object
     */
    ApplicationClientDTO registerClient(ApplicationClientDTO dto);
}