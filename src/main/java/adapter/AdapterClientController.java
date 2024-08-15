package adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ai.shreds.application.ApplicationClientDTO;
import ai.shreds.application.ApplicationClientInputPort;
import shared.AdapterRegisterClientRequest;
import shared.AdapterRegisterClientResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import exception.AdapterClientControllerException;
import mapper.AdapterClientMapper;

@RestController
@RequestMapping("/clients")
public class AdapterClientController {

    @Autowired
    private ApplicationClientInputPort applicationClientInputPort;

    @Autowired
    private AdapterClientMapper adapterClientMapper;

    @PostMapping("/register")
    public ResponseEntity<AdapterRegisterClientResponse> registerNewClient(@RequestBody AdapterRegisterClientRequest request) {
        try {
            ApplicationClientDTO clientDTO = adapterClientMapper.toDTO(request);
            ApplicationClientDTO registeredClientDTO = applicationClientInputPort.registerClient(clientDTO);
            AdapterRegisterClientResponse response = adapterClientMapper.toResponse(registeredClientDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new AdapterClientControllerException().handleException(e);
        }
    }
}