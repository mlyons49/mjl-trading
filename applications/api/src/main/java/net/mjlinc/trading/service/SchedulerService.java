package net.mjlinc.trading.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SchedulerService {

	@Autowired
	protected AlgoService algoService;

	@Value("${mjl-trading.position-size}")
	private Integer positionSize;
	@Value("${mjl-trading.max-positions}")
	private Integer maxPositions;
	
	@Value("${mjl-trading.backtest}")
	protected Boolean backtest;
	
    @Scheduled(fixedRate = 30000)
    public void processLiveTrading() {
    	if (!this.backtest) {
	        this.algoService.process(LocalDateTime.now());
    	} else {
    		log.info("No ScheduleService.");
    	}
    }

}
