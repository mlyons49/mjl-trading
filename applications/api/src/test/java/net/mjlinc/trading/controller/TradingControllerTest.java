package net.mjlinc.trading.controller;

import net.mjlinc.trading.clients.AlpacaClient;
import net.mjlinc.trading.model.Account;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TradingControllerTest {

    @Mock
    private AlpacaClient alpacaClient;

    @InjectMocks
    private TradingController tradingController;

    @Test
    public void testRetrieveAccount() throws Exception {
        Account mockAccount = new Account();
        when(alpacaClient.retrieveAccount(isA(String.class), isA(String.class))).thenReturn(mockAccount);
        Account actual = tradingController.retrieveAccount("apiKeyIdVal", "apiSecretKeyVal");
        Mockito.verify(alpacaClient).retrieveAccount(isA(String.class), isA(String.class));
        Assert.assertEquals(mockAccount, actual);
    }
}