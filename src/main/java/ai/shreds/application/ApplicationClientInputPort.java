package ai.shreds.application;

import ai.shreds.adapter.AdapterContactInfo;
import ai.shreds.adapter.AdapterAddress;
import java.util.UUID;
import java.time.LocalDateTime;
import ai.shreds.application.ApplicationClientDTO;

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