package net.mjlinc.trading.controller;

import lombok.extern.slf4j.Slf4j;
import net.mjlinc.trading.clients.AlpacaClient;
import net.mjlinc.trading.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TradingController {

    @Autowired
    private AlpacaClient alpacaClient;

    @GetMapping("/v1/account")
    public Account retrieveAccount(
            @RequestHeader(AlpacaClient.APCA_API_KEY_ID) String apiKeyId,
            @RequestHeader(AlpacaClient.APCA_API_SECRET_KEY) String apiSecretKey )
    {
        return alpacaClient.retrieveAccount(apiKeyId, apiSecretKey);
    }

}
