package net.mjlinc.trading.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force=true)
public class Calendar {
	 private String date;
	 private String open;
	 private String close;
}
