package ai.shreds.adapter;

import ai.shreds.application.ApplicationCreateSupplierDTO;
import ai.shreds.application.ApplicationUpdateSupplierDTO;
import ai.shreds.application.ApplicationSupplierDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Mapper class to convert between Adapter layer's request/response objects and Application layer's DTOs.
 */
@Slf4j
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
            log.error("AdapterCreateSupplierRequest is null");
            throw new IllegalArgumentException("AdapterCreateSupplierRequest cannot be null");
        }
        return ApplicationCreateSupplierDTO.builder()
                .name(request.getName())
                .contactInfoPhone(request.getContactInfoPhone())
                .contactInfoEmail(request.getContactInfoEmail())
                .address(request.getAddress())
                .zipCode(request.getZipCode())
                .city(request.getCity())
                .rc(request.getRc())
                .echeanceDate(request.getEcheanceDate())
                .build();
    }

    /**
     * Converts an AdapterUpdateSupplierRequest to an ApplicationUpdateSupplierDTO.
     *
     * @param request the AdapterUpdateSupplierRequest object
     * @return the ApplicationUpdateSupplierDTO object
     */
    public ApplicationUpdateSupplierDTO toApplicationUpdateDTO(AdapterUpdateSupplierRequest request) {
        if (request == null) {
            log.error("AdapterUpdateSupplierRequest is null");
            throw new IllegalArgumentException("AdapterUpdateSupplierRequest cannot be null");
        }
        return ApplicationUpdateSupplierDTO.builder()
                .name(request.getName())
                .contactInfoPhone(request.getContactInfoPhone())
                .contactInfoEmail(request.getContactInfoEmail())
                .address(request.getAddress())
                .zipCode(request.getZipCode())
                .city(request.getCity())
                .rc(request.getRc())
                .echeanceDate(request.getEcheanceDate())
                .build();
    }

    /**
     * Converts an ApplicationSupplierDTO to an AdapterSupplierResponse.
     *
     * @param dto the ApplicationSupplierDTO object
     * @return the AdapterSupplierResponse object
     */
    public AdapterSupplierResponse toAdapterResponse(ApplicationSupplierDTO dto) {
        if (dto == null) {
            log.error("ApplicationSupplierDTO is null");
            throw new IllegalArgumentException("ApplicationSupplierDTO cannot be null");
        }
        return AdapterSupplierResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .contactInfoPhone(dto.getContactInfoPhone())
                .contactInfoEmail(dto.getContactInfoEmail())
                .address(dto.getAddress())
                .zipCode(dto.getZipCode())
                .city(dto.getCity())
                .rc(dto.getRc())
                .echeanceDate(dto.getEcheanceDate())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }
}