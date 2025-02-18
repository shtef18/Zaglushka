package com.example.Controller;

import com.example.Model.RequestDTO;
import com.example.Model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;

@RestController
public class MainController {
    private Logger logger = LoggerFactory.getLogger(MainController.class);
    private ObjectMapper mapper = new ObjectMapper();

    public MainController() {

    }

    @PostMapping(
            value = "/info/postBalances",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Object postBalances(@RequestBody RequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();

        try {
            String clientId = requestDTO.getClientId();
            char firstDigit = clientId.charAt(0);
            BigDecimal maxLimit;
            String rqUID = requestDTO.getRqUID();
            if (firstDigit == '8') {
                maxLimit = new BigDecimal(2000.00);
                responseDTO.setCurrency("US");
            } else if (firstDigit == '9') {
                maxLimit = new BigDecimal(1000.00);
                responseDTO.setCurrency("EU");
            } else {
                maxLimit = new BigDecimal(10000.00);
                responseDTO.setCurrency("RUB");
            }

            responseDTO.setRqUID(rqUID);
            responseDTO.setClientId(clientId);
            responseDTO.setAccount(requestDTO.getAccount());
            responseDTO.setBalance(new BigDecimal(777));
            responseDTO.setMaxLimit(maxLimit);

            logger.error("********** RequestDTO **********" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            logger.error("********** ResponseDTO **********" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));
            return responseDTO;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

}
