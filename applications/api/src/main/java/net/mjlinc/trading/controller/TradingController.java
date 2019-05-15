package net.mjlinc.trading.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.mjlinc.trading.clients.AlpacaClient;
import net.mjlinc.trading.jpa.entities.BarEntity;
import net.mjlinc.trading.jpa.repositories.BarRepo;
import net.mjlinc.trading.model.Account;
import net.mjlinc.trading.model.Bar;

@Slf4j
@RestController
public class TradingController {

    @Autowired
    private AlpacaClient alpacaClient;

    @Autowired
    private BarRepo barRepo;

    @GetMapping("/v1/account")
    public Account retrieveAccount(
            @RequestHeader(AlpacaClient.APCA_API_KEY_ID) String apiKeyId,
            @RequestHeader(AlpacaClient.APCA_API_SECRET_KEY) String apiSecretKey )
    {
        return alpacaClient.retrieveAccount(apiKeyId, apiSecretKey);
    }

    @GetMapping("/v1/bars/{timeframe}")
    public List<Bar> retrieveBars(
            @RequestHeader(AlpacaClient.APCA_API_KEY_ID) String apiKeyId,
            @RequestHeader(AlpacaClient.APCA_API_SECRET_KEY) String apiSecretKey,
            @PathVariable("timeframe") String timeframe,
            @RequestParam("symbols") String symbols)
    {
        List<Bar> bars = alpacaClient.retrieveBars(apiKeyId, apiSecretKey, timeframe, symbols);
        List<BarEntity> barsEntities = buildBarEntities(symbols, timeframe, bars);
        barRepo.saveAll(barsEntities);
        return bars;
    }

    private List<BarEntity> buildBarEntities(String symbol, String timeframe, List<Bar> bars) {
        return bars.stream().map( (bar) -> buildBarEntity(symbol, timeframe, bar)).collect(Collectors.toList());
    }

    private BarEntity buildBarEntity(String symbol, String timeframe, Bar bar) {
        long longValue = bar.getT();
        LocalDateTime t = LocalDateTime.ofInstant(Instant.ofEpochMilli(longValue), ZoneId.systemDefault());
        return BarEntity.builder()
                .s( symbol )
                .t( t )
                .i( timeframe )
                .o( bar.getO() )
                .c( bar.getC() )
                .h( bar.getH() )
                .l( bar.getL() )
                .v( bar.getV() )
                .build();
    }

}
