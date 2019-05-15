package net.mjlinc.trading.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import net.mjlinc.trading.model.Bar;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MarketDataServiceIntTest {

	@Autowired
	private MarketDataService marketDataService;
	@Autowired
	private Environment environment;

	private String apiKeyId;
	private String apiSecretKey;

	@Before
	public void setup() {
		this.apiKeyId = environment.getProperty("APCA_API_KEY_ID");
		this.apiSecretKey = environment.getProperty("APCA_API_SECRET_KEY");
		log.info("this.apiKeyId {}", this.apiKeyId);
		log.info("this.apiSecretKey {}", this.apiSecretKey);
	}

	@Test
	public void testRetrieveBars_MSFT_1M() {
		String symbol = "MSFT";

		List<Bar> bars = this.marketDataService.retrieveBars(symbol, 100);

		log.info("bars.size(): {}", bars.size());
		Assert.assertNotNull(bars);
	}

	@Test
	public void testRetrieveBars_AMZN_1M() {
		String symbol = "AMZN";

		List<Bar> bars = this.marketDataService.retrieveBars(symbol, 100);

		log.info("bars.size(): {}", bars.size());
		Assert.assertNotNull(bars);
	}

	@Test
	public void testRetrieveBars_SPY_1M() {
		String symbol = "SPY";

		List<Bar> bars = this.marketDataService.retrieveBars(symbol, 100);

		log.info("bars.size(): {}", bars.size());
		Assert.assertNotNull(bars);
	}

}