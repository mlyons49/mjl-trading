package net.mjlinc.trading.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force=true)
public class Position {
    String assetId;
    String symbol;
    String exchange;
    String assetClass;
    String avgEntryPrice;
    String qty;
    String side;
    String marketValue;
    String costBasis;
    String unrealizedPl;
    String unrealizedPpc;
    String unrealizedIntradayPl;
    String unrealizedIntradayPlpc;
    String currentPrice;
    String lastdayPrice;
    String changeToday;
}
