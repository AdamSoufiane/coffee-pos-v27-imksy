package exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import shared.AdapterRegisterClientResponse;

public class AdapterClientControllerException {

    public ResponseEntity<AdapterRegisterClientResponse> handleException(Exception e) {
        AdapterRegisterClientResponse response = new AdapterRegisterClientResponse();
        response.setFirstName("Error");
        response.setLastName("Occurred");
        response.setEmail("error@example.com");
        response.setPhoneNumber("000-000-0000");
        response.setAddress(null);
        response.setRegistrationDate(null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}