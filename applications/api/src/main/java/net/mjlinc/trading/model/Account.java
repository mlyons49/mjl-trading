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
	 private boolean patternDayTrader;
	 private boolean tradingBlocked;
	 private boolean transfersBlocked;
	 private boolean accountBlocked;
	 private String createdAt;
}
