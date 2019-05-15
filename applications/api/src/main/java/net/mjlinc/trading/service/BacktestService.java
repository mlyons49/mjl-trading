package net.mjlinc.trading.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BacktestService extends SchedulerService {

	@Value("${mjl-trading.backtest}")
	private Boolean backtest;
	@Value("${mjl-trading.backtest.days}")
	private Integer backtestDays;
	@Value("${mjl-trading.backtest.equity}")
	private BigDecimal backtestEquity;
	@Value("${mjl-trading.backtest.benchmark}")
	private String backtestBenchmark;

	private Boolean running = Boolean.FALSE;
	
    @Scheduled(fixedRate = 30000)
	public void init() {
    	if (this.backtest && !this.running) {
    		this.running = Boolean.TRUE;
    		processBacktestTrading();
    	}
	}

    public void processBacktestTrading() {
        log.info("Backtest is starting...");
        
		LocalDateTime currDateTime = LocalDateTime.now();
		//TODO: load realm data going back (days + plus algo req)
		
		LocalDateTime backtestDateTime = currDateTime.minus(backtestDays, ChronoUnit.DAYS).withHour(8).withMinute(25);
		while(backtestDateTime.isBefore(currDateTime)) {
			this.algoService.process(backtestDateTime);
			sleep(500);
			backtestDateTime = backtestDateTime.plus(1, ChronoUnit.MINUTES);
		}
        log.info("Backtest is complete.");
    }

    private void sleep(long millis) {
    	try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			log.error("Error pausing between backtest cycles.");
		}
    }
}
