package net.mjlinc.trading.clients;

import lombok.extern.slf4j.Slf4j;
import net.mjlinc.trading.model.Account;
import net.mjlinc.trading.model.Bar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class AlpacaClient {

    private String baseUrl = "https://paper-api.alpaca.markets";
    private String dataUrl = "https://data.alpaca.markets";
    public static final String APCA_API_KEY_ID = "APCA-API-KEY-ID";
    public static final String APCA_API_SECRET_KEY = "APCA-API-SECRET-KEY";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Environment environment;
    
    private String apiKeyId;
    private String apiSecretKey;
    
    @PostConstruct
    private void init() {
        this.apiKeyId = environment.getProperty("APCA_API_KEY_ID");
        this.apiSecretKey = environment.getProperty("APCA_API_SECRET_KEY");
    }

    public Account retrieveAccount(String apiKeyId, String apiSecretKey) {
        String uri = "/v1/account";

        HttpEntity<String> entity = buildHttpEntity(apiKeyId, apiSecretKey);

        log.debug("starting getAccount");
        ResponseEntity<Account> responseEntity = this.restTemplate.exchange(buildUrl(uri), HttpMethod.GET, entity, Account.class);
        log.debug("starting getAccount");
        return responseEntity.getBody();
    }

    private HttpEntity<String> buildHttpEntity(String apiKeyId, String apiSecretKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(APCA_API_KEY_ID,apiKeyId);
        headers.add(APCA_API_SECRET_KEY, apiSecretKey);
        return new HttpEntity<>("paramters", headers);
    }

    public List<Bar> retrieveBars(String timeframe, String symbols) {
       return this.retrieveBars(this.apiKeyId, this.apiSecretKey, timeframe, symbols);

    }
    public List<Bar> retrieveBars(String apiKeyId, String apiSecretKey, String timeframe, String symbols) {
        // "1Min, 5Min, 15Min, 1D"
        String uri = "/v1/bars/"+timeframe + "?symbols="+symbols;

        HttpEntity<String> entity = buildHttpEntity(apiKeyId, apiSecretKey);

        ResponseEntity<Map> responseEntity = this.restTemplate.exchange(buildDataUrl(uri), HttpMethod.GET, entity, Map.class);

        Map map = responseEntity.getBody();
        List<Map<String, Object>> l = (List)map.get(symbols);
        List<Bar> bars = new ArrayList<>();
        for(Map<String, Object> m : l) {
            bars.add( Bar.builder()
                        .t( (Long)m.get("t") )
                        .o( new BigDecimal( (m.get("o")).toString() ))
                        .h( new BigDecimal( (m.get("h")).toString() ))
                        .l( new BigDecimal( (m.get("l")).toString() ))
                        .c( new BigDecimal( (m.get("c")).toString() ))
                        .v( (Long)m.get("v") )
                        .build() );
        }
        return bars;
    }

    private String buildUrl(String uri) {
        return this.baseUrl + uri;
    }
    private String buildDataUrl(String uri) {
        return this.dataUrl + uri;
    }

}
