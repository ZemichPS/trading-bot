package by.zemich.bargainms.infrastructure.clients;

import by.zemich.bargainms.domain.valueobject.Interval;
import com.binance.connector.client.SpotClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class BinanceClient {
    private final SpotClient spotClient;

    public String klines(Map<String, Object> params) {
        return spotClient.createMarket().klines(params);
    }

    public String createOrder(Map<String, Object> params) {
        return spotClient.createTrade().newOrder(params);
    }
}
