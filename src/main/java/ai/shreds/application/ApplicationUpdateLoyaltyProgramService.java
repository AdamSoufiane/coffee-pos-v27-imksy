package ai.shreds.application;

import ai.shreds.adapter.AdapterUpdateLoyaltyProgramRequest;
import ai.shreds.adapter.AdapterUpdateLoyaltyProgramResponse;
import ai.shreds.adapter.AdapterLoyaltyProgramMapper;
import ai.shreds.domain.DomainLoyaltyProgramManagerService;
import ai.shreds.domain.DomainValidationException;
import ai.shreds.domain.DomainResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service implementation for updating loyalty program details.
 */
@Slf4j
@Validated
@Service
@RequiredArgsConstructor
public class ApplicationUpdateLoyaltyProgramService implements ApplicationUpdateLoyaltyProgramInputPort {

    private final DomainLoyaltyProgramManagerService loyaltyProgramManagerService;
    private final AdapterLoyaltyProgramMapper loyaltyProgramMapper;

    /**
     * Updates the details of an existing loyalty program for a client.
     *
     * @param request the request containing the updated loyalty program details
     * @return the response with the status and updated details
     */
    @Override
    @Transactional
    public AdapterUpdateLoyaltyProgramResponse updateLoyaltyProgram(@Validated @NotNull AdapterUpdateLoyaltyProgramRequest request) {
        log.info("Starting updateLoyaltyProgram method with request: {}", request);
        try {
            // Validate input data
            validateRequest(request);

            // Map input request to domain entity
            var loyaltyProgramEntity = loyaltyProgramMapper.mapToDomainEntity(request);

            // Update loyalty program details
            loyaltyProgramManagerService.updateLoyaltyProgram(
                request.getClientId(),
                request.getLoyaltyProgramId(),
                request.getProgramName(),
                request.getDescription(),
                request.getBenefits(),
                request.getEnrollmentDate()
            );

            // Create domain response DTO
            DomainResponseDto domainResponse = new DomainResponseDto();
            domainResponse.setStatusCode(200);
            domainResponse.setMessage("Loyalty program updated successfully");
            domainResponse.setClientId(request.getClientId());
            domainResponse.setLoyaltyProgramId(request.getLoyaltyProgramId());

            // Map domain response to adapter response
            AdapterUpdateLoyaltyProgramResponse adapterResponse = loyaltyProgramMapper.mapToAdapterResponse(domainResponse);
            log.info("Exiting updateLoyaltyProgram method with successful response: {}", adapterResponse);
            return adapterResponse;
        } catch (DomainValidationException e) {
            log.error("Validation error: {}", e.getMessage());
            throw new ApplicationValidationException("Validation error: " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            throw new ApplicationLoyaltyProgramException("Unexpected error: " + e.getMessage());
        } finally {
            log.info("Ending updateLoyaltyProgram method");
        }
    }

    /**
     * Validates the request to ensure all required fields are provided and valid.
     *
     * @param request the request to validate
     * @throws DomainValidationException if any required field is missing or invalid
     */
    private void validateRequest(AdapterUpdateLoyaltyProgramRequest request) throws DomainValidationException {
        if (request.getClientId() == null || request.getLoyaltyProgramId() == null ||
            request.getProgramName() == null || request.getDescription() == null ||
            request.getBenefits() == null || request.getEnrollmentDate() == null) {
            throw new DomainValidationException("All required fields must be provided and valid. ClientId: " + request.getClientId() + ", LoyaltyProgramId: " + request.getLoyaltyProgramId() + ", ProgramName: " + request.getProgramName() + ", Description: " + request.getDescription() + ", Benefits: " + request.getBenefits() + ", EnrollmentDate: " + request.getEnrollmentDate());
        }
    }
}