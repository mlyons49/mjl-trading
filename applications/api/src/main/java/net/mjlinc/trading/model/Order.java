package net.mjlinc.trading.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force=true)
public class Order {
    String id;
    String clientOrderId;
    String createdAt;
    String updatedAt;
    String submittedAt;
    String filledAt;
    String expiredAt;
    String canceledAt;
    String failedAt;
    String assetId;
    String symbol;
    String assetClass;
    String qty;
    String filledQty;
    String type;
    String side;
    String timeInForce;
    String limitPrice;
    String stopPrice;
    String filledAvgPrice;
    String status;
}
