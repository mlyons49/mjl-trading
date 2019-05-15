package net.mjlinc.trading;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MjlTradingApiTests {


	@Test
	public void contextLoads() {
	}

	@Test
	public void testRestTemplate() throws Exception {
		MjlTradingApi mjlTradingApi = new MjlTradingApi();
		RestTemplate rt = mjlTradingApi.restTemplate();
		Assert.assertNotNull(rt);
	}
}
