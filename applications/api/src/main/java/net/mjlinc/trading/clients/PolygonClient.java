package net.mjlinc.trading.clients;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.mjlinc.trading.model.Bar;
import net.mjlinc.trading.model.polygon.PolygonAggsResponse;

@Component
@Slf4j
public class PolygonClient {

    private String baseUrl = "https://api.polygon.io";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Environment environment;
    
    private String apiKeyId;
    
    @PostConstruct
    private void init() {
        this.apiKeyId = environment.getProperty("APCA_API_KEY_ID");
    }

    public List<Bar> retrieveBars(String symbol, LocalDate startDate, LocalDate endDate) {

    	String start = startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    	String end = endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String uri = "/v2/aggs/ticker/" + symbol + "/range/1/minute/" + start + "/" + end + appendApiKeyParam();
        String url = buildUrl(uri);
        log.debug("Calling: " + url);
        
        ResponseEntity<PolygonAggsResponse> resp = this.restTemplate.getForEntity(url, PolygonAggsResponse.class);
        List<Bar> bars = resp.getBody().getResults();
        log.debug("Returned {} bars.", bars.size());
        return bars;
    }

    private String buildUrl(String uri) {
        return this.baseUrl + uri;
    }
    
    private String appendApiKeyParam() {
    	return "?apiKey=" + this.apiKeyId;
    }
    

}
