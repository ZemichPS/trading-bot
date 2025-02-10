package by.zemich.bargainms.application.projection;


import by.zemich.bargainms.domain.event.OrderStatusUpdatedEvent;
import by.zemich.bargainms.infrastructure.repository.jpa.OrderRepository;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderProjection {
    private final OrderRepository repository;

    @EventHandler
    public void on(OrderStatusUpdatedEvent event){
        Long orderId = event.getOrderId();
        repository.findById(orderId).ifPresent(order -> {
            order.setExecutedQty(event.getExecutedQty());
            order.setOrigQty(event.getOrigQty());
            order.setCummulativeQuoteQty(event.getCummulativeQuoteQty());
            order.setPrice(event.getPrice());
            order.setStatus(event.getStatus());
            repository.save(order);
        });
    }
}
