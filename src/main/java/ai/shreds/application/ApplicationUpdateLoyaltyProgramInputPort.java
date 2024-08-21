package ai.shreds.application;

import ai.shreds.shared.AdapterUpdateLoyaltyProgramRequest;
import ai.shreds.shared.AdapterUpdateLoyaltyProgramResponse;

/**
 * Interface for updating loyalty program details for a client.
 */
public interface ApplicationUpdateLoyaltyProgramInputPort {

    /**
     * Updates the details of an existing loyalty program for a client.
     *
     * @param request the request object containing the updated loyalty program details
     * @return the response object containing the status and updated loyalty program details
     */
    AdapterUpdateLoyaltyProgramResponse updateLoyaltyProgram(AdapterUpdateLoyaltyProgramRequest request);
}