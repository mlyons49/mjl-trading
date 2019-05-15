package net.mjlinc.trading.transformers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import net.mjlinc.trading.jpa.entities.BarEntity;
import net.mjlinc.trading.model.Bar;

@Component
public class BarTransformer {

	public List<Bar> buildBarModels(List<BarEntity> barsFromDb) {
		return barsFromDb.stream().map( (barEntity) -> 
				buildBarModel(barEntity)
			).collect(Collectors.toList());
	}
	
	private Bar buildBarModel(BarEntity barEntity) {
		ZonedDateTime zdt = barEntity.getT().atZone(TimeZone.getDefault().toZoneId());
		return Bar.builder()
				.t(zdt.toInstant().toEpochMilli())
				.h(barEntity.getH())
				.o(barEntity.getO())
				.l(barEntity.getL())
				.c(barEntity.getC())
				.v(barEntity.getV())
				.build();
	}
	
    public List<BarEntity> buildBarEntities(String symbol, String timeframe, List<Bar> bars) {
        return bars.stream().map( (bar) -> 
        		buildBarEntity(symbol, timeframe, bar) 
        	).collect(Collectors.toList());
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
