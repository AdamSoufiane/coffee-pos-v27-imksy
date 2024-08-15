package adapter;

import shared.AdapterRegisterClientRequest;
import shared.AdapterRegisterClientResponse;
import application.ApplicationClientDTO;
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
        dto.setContact_info(new AdapterContactInfo(request.getPhoneNumber(), request.getEmail()));
        dto.setAddress(new AdapterAddress(request.getAddress().getAddress(), request.getAddress().getZipCode(), request.getAddress().getCity()));
        dto.setRegistrationDate(LocalDateTime.now());
        return dto;
    }

    public AdapterRegisterClientResponse toResponse(ApplicationClientDTO dto) {
        AdapterRegisterClientResponse response = new AdapterRegisterClientResponse();
        response.setClientId(dto.getClientId());
        response.setFirstName(dto.getFirstName());
        response.setLastName(dto.getLastName());
        response.setEmail(dto.getContact_info().getEmail());
        response.setPhoneNumber(dto.getContact_info().getPhone());
        response.setAddress(new AdapterAddress(dto.getAddress().getAddress(), dto.getAddress().getZipCode(), dto.getAddress().getCity()));
        response.setRegistrationDate(dto.getRegistrationDate());
        return response;
    }
}