package net.mjlinc.trading.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.mjlinc.trading.clients.PolygonClient;
import net.mjlinc.trading.jpa.entities.BarEntity;
import net.mjlinc.trading.jpa.repositories.BarRepo;
import net.mjlinc.trading.model.Bar;
import net.mjlinc.trading.transformers.BarTransformer;

@Service
@Slf4j
public class MarketDataService {

	@Autowired
	private PolygonClient polygonClient; 
	@Autowired
	private BarRepo barRepo;
	@Autowired
	private BarTransformer barTransformer;
	
    public List<Bar> retrieveBars(String symbol, LocalDate startDate, LocalDate endDate) {
    	log.info("calling db");
    	List<BarEntity> barsFromDb = barRepo.findBySymboldAndDateRange(symbol, startDate, endDate);
    	if (barsFromDb.size() > 0) {
        	log.info("back from db {}",barsFromDb.size());
    		return barTransformer.buildBarModels(barsFromDb);
    	}
    	
    	List<Bar> barsFromService = polygonClient.retrieveBars(symbol, startDate, endDate);
    	saveBarsToDb(symbol, "1M", barsFromService);
    	return barsFromService;
    }
    
    public List<Bar> retrieveBars(String symbol, Integer pastDays) {
    	LocalDate endDate = LocalDate.now();
    	LocalDate startDate = endDate.minusDays(pastDays);
    	List<Bar> barsFromService = retrieveBars(symbol, startDate, endDate);
    	return barsFromService;
    }
    
    private void saveBarsToDb(String symbol, String timeframe, List<Bar> barsFromService) {
        List<BarEntity> barsEntities =barTransformer.buildBarEntities(symbol, timeframe, barsFromService);
        barRepo.saveAll(barsEntities);
    }
    
}
