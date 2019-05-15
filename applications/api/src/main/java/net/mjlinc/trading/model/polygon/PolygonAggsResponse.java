package net.mjlinc.trading.model.polygon;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import net.mjlinc.trading.model.Bar;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force=true)
public class PolygonAggsResponse {
	private String ticker;
	private String status;
	private Integer queryCount;
	private Integer resultsCount;
	private Boolean adjusted;
	private List<Bar> results;
}
