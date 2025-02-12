package by.zemich.bargainms.infrastructure.scheduled;

import by.zemich.bargainms.domain.event.OrderStatusChangedEvent;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@AllArgsConstructor
public class OrderScheduled {

    private final EventGateway eventGateway;

    @Scheduled(fixedRate = 10_000)
    public void checkStatus(){
        OrderStatusChangedEvent event = OrderStatusChangedEvent.builder().build();
        eventGateway.publish(event);
    }
}
