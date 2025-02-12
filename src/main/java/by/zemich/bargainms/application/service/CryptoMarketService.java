package by.zemich.bargainms.application.service;

import by.zemich.bargainms.application.dto.BinanceOrderResponse;
import by.zemich.bargainms.application.mapper.DtoToMapMapper;
import by.zemich.bargainms.domain.command.PlaceBuyOrderCommand;
import by.zemich.bargainms.domain.event.OrderPlacedEvent;
import by.zemich.bargainms.domain.request.CreateOrderRequest;
import by.zemich.bargainms.domain.valueobject.OrderSide;
import by.zemich.bargainms.domain.valueobject.OrderType;
import by.zemich.bargainms.domain.valueobject.TimeInForce;
import by.zemich.bargainms.infrastructure.clients.BinanceClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;


@Service
@AllArgsConstructor
public class CryptoMarketService {

    private final BinanceClient binanceClient;
    private final DtoToMapMapper dtoToMapMapper;
    private final ObjectMapper objectMapper;
    private final EventGateway eventGateway;

    @CommandHandler
    public void handle(PlaceBuyOrderCommand command){
        CreateOrderRequest request = CreateOrderRequest.builder()
                .symbol(command.getSymbol())
                .timeInForce(command.getTimeInForce())
                .type(OrderType.LIMIT)
                .price(command.getPrice())
                .side(OrderSide.BUY)
                .quantity(command.getQuantity())
                .build();
        BinanceOrderResponse response = placeOrder(request);
        OrderPlacedEvent event = OrderPlacedEvent.builder().build();
        BeanUtils.copyProperties(response, event);
        eventGateway.publish(event);
    }

    public BinanceOrderResponse placeBuyLimitOrder(String symbol, BigDecimal price, BigDecimal quantity, TimeInForce timeInForce) {
        CreateOrderRequest request = CreateOrderRequest.builder()
                .symbol(symbol)
                .timeInForce(timeInForce)
                .type(OrderType.LIMIT)
                .price(price)
                .side(OrderSide.BUY)
                .quantity(quantity)
                .build();

        return placeOrder(request);
    }

    public BinanceOrderResponse placeSellLimitOrder(String symbol, BigDecimal price, BigDecimal quantity, TimeInForce timeInForce) {
        CreateOrderRequest request = CreateOrderRequest.builder()
                .symbol(symbol)
                .timeInForce(timeInForce)
                .type(OrderType.LIMIT)
                .price(price)
                .side(OrderSide.SELL)
                .quantity(quantity)
                .build();

        return placeOrder(request);
    }

    private BinanceOrderResponse placeOrder(CreateOrderRequest request) {
        Map<String, Object> params = dtoToMapMapper.map(request);
        String binanceResponse = binanceClient.createOrder(params);
        return objectMapper.convertValue(binanceResponse, BinanceOrderResponse.class);
    }

}
