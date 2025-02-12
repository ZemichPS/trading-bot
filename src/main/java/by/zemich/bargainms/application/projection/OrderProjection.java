package by.zemich.bargainms.application.projection;


import by.zemich.bargainms.domain.event.OrderSavedEvent;
import by.zemich.bargainms.domain.event.OrderStatusChangedEvent;
import by.zemich.bargainms.infrastructure.repository.jpa.BargainRepository;
import by.zemich.bargainms.infrastructure.repository.jpa.OrderRepository;
import by.zemich.bargainms.infrastructure.repository.jpa.entity.BargainEntity;
import by.zemich.bargainms.infrastructure.repository.jpa.entity.OrderEntity;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderProjection {
    private final OrderRepository orderRepository;
    private final BargainRepository bargainRepository;

    @EventHandler
    public void on(OrderSavedEvent event) {
        BargainEntity bargain = bargainRepository.findById(event.getBargainId()).get();
        OrderEntity order = OrderEntity.builder()
                .symbol(event.getSymbol())
                .orderId(event.getOrderId())
                .clientOrderId(event.getClientOrderId())
                .clientOrderId(event.getClientOrderId())
                .transactTime(event.getTransactTime())
                .price(event.getPrice())
                .origQty(event.getOrigQty())
                .executedQty(event.getExecutedQty())
                .cummulativeQuoteQty(event.getCummulativeQuoteQty())
                .status(event.getStatus())
                .timeInForce(event.getTimeInForce())
                .type(event.getType())
                .side(event.getSide())
                .build();
        bargain.addOrder(order);
        bargainRepository.save(bargain);
    }

    @EventHandler
    public void on(OrderStatusChangedEvent event) {
        Long orderId = event.getOrderId();
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setExecutedQty(event.getExecutedQty());
            order.setOrigQty(event.getOrigQty());
            order.setCummulativeQuoteQty(event.getCummulativeQuoteQty());
            order.setPrice(event.getPrice());
            order.setStatus(event.getStatus());
            orderRepository.save(order);
        });
    }
}
