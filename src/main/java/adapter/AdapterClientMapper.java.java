package adapter;

import shared.AdapterRegisterClientRequest;
import shared.AdapterRegisterClientResponse;
import ai.shreds.application.ApplicationClientDTO;
import shared.AdapterContactInfo;
import shared.AdapterAddress;
import java.util.UUID;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class AdapterClientMapper {

    public ApplicationClientDTO toDTO(AdapterRegisterClientRequest request) {
        ApplicationClientDTO dto = new ApplicationClientDTO();
        dto.setClientId(UUID.randomUUID());
        dto.setFirstName(request.getFirstName());
        dto.setLastName(request.getLastName());
        dto.setContactInfo(new AdapterContactInfo(request.getPhoneNumber(), request.getEmail()));
        dto.setAddress(new AdapterAddress(request.getAddress().getAddress(), request.getAddress().getZip_code(), request.getAddress().getCity()));
        dto.setRegistrationDate(LocalDateTime.now());
        return dto;
    }

    public AdapterRegisterClientResponse toResponse(ApplicationClientDTO dto) {
        AdapterRegisterClientResponse response = new AdapterRegisterClientResponse();
        response.setClientId(dto.getClientId());
        response.setFirstName(dto.getFirstName());
        response.setLastName(dto.getLastName());
        response.setEmail(dto.getContactInfo().getEmail());
        response.setPhoneNumber(dto.getContactInfo().getPhone());
        response.setAddress(new AdapterAddress(dto.getAddress().getAddress(), dto.getAddress().getZip_code(), dto.getAddress().getCity()));
        response.setRegistrationDate(dto.getRegistrationDate());
        return response;
    }
}