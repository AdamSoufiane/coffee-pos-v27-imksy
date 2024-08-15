package adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import application.ApplicationClientDTO;
import application.ApplicationClientInputPort;
import shared.AdapterRegisterClientRequest;
import shared.AdapterRegisterClientResponse;
import shared.AdapterClientControllerException;
import shared.AdapterClientMapper;

@RestController
@RequestMapping("/clients")
public class AdapterClientController {

    @Autowired
    private ApplicationClientInputPort applicationClientInputPort;

    @Autowired
    private AdapterClientMapper adapterClientMapper;

    @PostMapping("/register")
    public AdapterRegisterClientResponse registerNewClient(@RequestBody AdapterRegisterClientRequest request) {
        try {
            ApplicationClientDTO clientDTO = adapterClientMapper.toDTO(request);
            ApplicationClientDTO registeredClientDTO = applicationClientInputPort.registerClient(clientDTO);
            return adapterClientMapper.toResponse(registeredClientDTO);
        } catch (Exception e) {
            AdapterClientControllerException.handleException(e);
        }
        return null;
    }
}