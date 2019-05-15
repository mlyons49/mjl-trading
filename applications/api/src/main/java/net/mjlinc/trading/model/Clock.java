package net.mjlinc.trading.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force=true)
public class Clock {
	String timestamp;
	Boolean isOpen;
	String nextOpen;
	String next_close;
}
