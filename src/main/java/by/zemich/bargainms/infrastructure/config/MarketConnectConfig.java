package by.zemich.bargainms.infrastructure.config;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.impl.SpotClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarketConnectConfig {

    @Value("${binance.API_KEY}")
    private String apiKey;
    @Value("${binance.SECRET_KEY}")
    private String secretKey;

    @Bean
    SpotClient spotClient(){
        return new SpotClientImpl(apiKey, secretKey);
    };
}
