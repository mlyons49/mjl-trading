package net.mjlinc.trading.model;

import lombok.*;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force=true)
public class Account {
	 private String id;
	 private String status;
	 private String currency;
	 private String buyingPower;
	 private String cash;
	 private String cashWithdrawable;
	 private String portfolioValue;
	 private Boolean patternDayTrader;
	 private Boolean tradingBlocked;
	 private Boolean transfersBlocked;
	 private Boolean accountBlocked;
	 private String createdAt;
}
