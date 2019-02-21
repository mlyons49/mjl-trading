package net.mjlinc.trading.clients;

import lombok.extern.slf4j.Slf4j;
import net.mjlinc.trading.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class AlpacaClient {

    private String baseUrl = "https://paper-api.alpaca.markets";
    public static final String APCA_API_KEY_ID = "APCA-API-KEY-ID";
    public static final String APCA_API_SECRET_KEY = "APCA-API-SECRET-KEY";

    @Autowired
    private RestTemplate restTemplate;

    public Account retrieveAccount(String apiKeyId, String apiSecretKey) {
        String uri = "/v1/account";

        HttpHeaders headers = new HttpHeaders();
        headers.add(APCA_API_KEY_ID,apiKeyId);
        headers.add(APCA_API_SECRET_KEY, apiSecretKey);

        HttpEntity<String> entity = new HttpEntity<>("paramters", headers);

        log.debug("starting getAccount");
        ResponseEntity<Account> responseEntity = this.restTemplate.exchange(buildUrl(uri), HttpMethod.GET, entity, Account.class);
        log.debug("starting getAccount");
        return responseEntity.getBody();
    }

    private String buildUrl(String uri) {
        return this.baseUrl + uri;
    }

}
