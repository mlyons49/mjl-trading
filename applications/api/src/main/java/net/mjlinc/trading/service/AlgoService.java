package net.mjlinc.trading.service;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static org.apache.commons.lang3.ArrayUtils.contains;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AlgoService {

	public void initAlgo() {
		// loadAPI
		// api.loadAccount
		// api.loadPositions
	}
	
	public void process(LocalDateTime processTime) {
		if (!isTradingTime(processTime)) {
			return;
		}
		log.info("processing..." + processTime.toString());
		// check if validBuyingTime

		/*
		retrieve latest prices(time, positions, REALM)
			load any necessary historic data
		
		check for positionsToClose() (
			if market will close in n minutes  	
			process closes()
		)
		
		
		look for buys()
			rank buys
			check if too late to buy()
			
			buy(current positions, buy settings)
		 */
		
	}
	
	public boolean isTradingTime(LocalDateTime processTime) {
		if (!isWeekday(processTime))
			return false;
		if (!isTradingHours(processTime))
			return false;
		// if (isMarketOpen())
		return true;
	}
	
	public boolean isWeekday(LocalDateTime processTime) {
		DayOfWeek[] weekdays = {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY};
		if (contains(weekdays, processTime.getDayOfWeek())) {
			return true;
		}
		return false;
	}
	
	public boolean isTradingHours(LocalDateTime processTime) {
		int processHour = processTime.getHour();
		int processMinute = processTime.getMinute();
		if(processHour>8 ||  processHour==8 && processMinute>30) {
			if ( processHour<15 || (processHour==15 && processMinute<30) ) {
				return true;
			}
		}
		return false;
	}
}
