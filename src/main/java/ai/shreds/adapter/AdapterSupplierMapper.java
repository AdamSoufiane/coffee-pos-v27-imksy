package ai.shreds.adapter;

import ai.shreds.application.ApplicationSupplierDTO;
import shared.ApplicationCreateSupplierDTO;
import shared.ApplicationUpdateSupplierDTO;
import org.springframework.stereotype.Component;

/**
 * Mapper class to convert between Adapter layer's request/response objects and Application layer's DTOs.
 */
@Component
public class AdapterSupplierMapper {

    /**
     * Converts an AdapterCreateSupplierRequest to an ApplicationCreateSupplierDTO.
     *
     * @param request the AdapterCreateSupplierRequest object
     * @return the ApplicationCreateSupplierDTO object
     */
    public ApplicationCreateSupplierDTO toApplicationCreateDTO(AdapterCreateSupplierRequest request) {
        if (request == null) {
            System.err.println("AdapterCreateSupplierRequest is null");
            throw new IllegalArgumentException("AdapterCreateSupplierRequest cannot be null");
        }
        return new ApplicationCreateSupplierDTO(
                request.getName(),
                request.getContact_info_phone(),
                request.getContact_info_email(),
                request.getAddress(),
                request.getZip_code(),
                request.getCity(),
                request.getRc(),
                request.getEcheance_date()
        );
    }

    /**
     * Converts an AdapterUpdateSupplierRequest to an ApplicationUpdateSupplierDTO.
     *
     * @param request the AdapterUpdateSupplierRequest object
     * @return the ApplicationUpdateSupplierDTO object
     */
    public ApplicationUpdateSupplierDTO toApplicationUpdateDTO(AdapterUpdateSupplierRequest request) {
        if (request == null) {
            System.err.println("AdapterUpdateSupplierRequest is null");
            throw new IllegalArgumentException("AdapterUpdateSupplierRequest cannot be null");
        }
        return new ApplicationUpdateSupplierDTO(
                request.getName(),
                request.getContact_info_phone(),
                request.getContact_info_email(),
                request.getAddress(),
                request.getZip_code(),
                request.getCity(),
                request.getRc(),
                request.getEcheance_date()
        );
    }

    /**
     * Converts an ApplicationSupplierDTO to an AdapterSupplierResponse.
     *
     * @param dto the ApplicationSupplierDTO object
     * @return the AdapterSupplierResponse object
     */
    public AdapterSupplierResponse toAdapterResponse(ApplicationSupplierDTO dto) {
        if (dto == null) {
            System.err.println("ApplicationSupplierDTO is null");
            throw new IllegalArgumentException("ApplicationSupplierDTO cannot be null");
        }
        return new AdapterSupplierResponse(
                dto.getId(),
                dto.getName(),
                dto.getContact_info_phone(),
                dto.getContact_info_email(),
                dto.getAddress(),
                dto.getZip_code(),
                dto.getCity(),
                dto.getRc(),
                dto.getEcheance_date(),
                dto.getCreated_at(),
                dto.getUpdated_at()
        );
    }
}