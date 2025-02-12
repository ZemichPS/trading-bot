package by.zemich.bargainms.application.factory;

import by.zemich.bargainms.infrastructure.repository.jpa.entity.OrderEntity;
import by.zemich.bargainms.domain.event.OrderPlacedEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderEntityFactory {

    public OrderEntity eventToOrderEntity(OrderPlacedEvent event) {
        return OrderEntity.builder()
                .orderId(event.getOrderId())
                .clientOrderId(event.getClientOrderId())
                .price(event.getPrice())
                .asset(event.getAsset())
                .cummulativeQuoteQty(event.getCummulativeQuoteQty())
                .side(event.getSide())
                .executedQty(event.getExecutedQty())
                .status(event.getOrderStatus())
                .symbol(event.getSymbol())
                .transactTime(event.getTransactTime())
                .timeInForce(event.getTimeInForce())
                .symbol(event.getSymbol())
                .status(event.getOrderStatus())
                .build();
    }
}
