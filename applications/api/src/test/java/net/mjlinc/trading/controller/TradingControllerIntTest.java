package net.mjlinc.trading.controller;


import lombok.extern.slf4j.Slf4j;
import net.mjlinc.trading.clients.AlpacaClient;
import net.mjlinc.trading.model.Account;
import net.mjlinc.trading.model.Bar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TradingControllerIntTest {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Environment environment;

    private String baseUrl = "http://localhost:8080";
    private String apiKeyId;
    private String apiSecretKey;

    @Before
    public void setup() {
        this.apiKeyId = environment.getProperty("APCA_API_KEY_ID");
        this.apiSecretKey = environment.getProperty("APCA_API_SECRET_KEY");
        log.info("this.apiKeyId {}",this.apiKeyId);
        log.info("this.apiSecretKey {}",this.apiSecretKey);
    }

    @Test
    public void testAccount() {
        String uri = "/v1/account";

        HttpEntity<String> entity = buildHttpEntity();
        ResponseEntity<Account> responseEntity = this.restTemplate.exchange(buildUrl(uri), HttpMethod.GET, entity, Account.class);
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
        Account account = responseEntity.getBody();
//        Assert.assertEquals("ABC", account.getId() );
        Assert.assertEquals("ACTIVE", account.getStatus() );
        Assert.assertEquals("USD", account.getCurrency() );
        Assert.assertEquals("100000", account.getCash() );
    }

    @Test
    public void testRetrieveBars() {
        String uri = "/v1/bars/1D?symbols=MSFT";
        HttpEntity<String> entity = buildHttpEntity();
        ResponseEntity<Bar[]> responseEntity = this.restTemplate.exchange(buildUrl(uri), HttpMethod.GET, entity, Bar[].class);
        Bar[] bars = responseEntity.getBody();
        Assert.assertEquals(100, bars.length);
        for(Bar bar: bars) {
            log.info(bar.toString());
        }

    }

    private HttpEntity<String> buildHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(AlpacaClient.APCA_API_KEY_ID, apiKeyId);
        headers.add(AlpacaClient.APCA_API_SECRET_KEY, apiSecretKey);
        return new HttpEntity<>("paramters", headers);
    }


    private String buildUrl(String uri) {
        return this.baseUrl + uri;
    }

}