package by.zemich.bargainms.application.projection;

import by.zemich.bargainms.application.factory.BargainEntityFactory;
import by.zemich.bargainms.application.factory.OrderEntityFactory;
import by.zemich.bargainms.infrastructure.repository.jpa.entity.BargainEntity;
import by.zemich.bargainms.infrastructure.repository.jpa.entity.OrderEntity;
import by.zemich.bargainms.domain.event.BargainInitiatedEvent;
import by.zemich.bargainms.domain.event.OrderPlacedEvent;
import by.zemich.bargainms.infrastructure.repository.jpa.BargainRepository;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class BargainProjection {

    private final BargainEntityFactory bargainEntityFactory;
    private final OrderEntityFactory orderEntityFactory;
    private final BargainRepository repository;

    @EventHandler
    public void on(BargainInitiatedEvent event) {
        BargainEntity bargainEntity = bargainEntityFactory.eventToBargainEntity(event);
        repository.save(bargainEntity);
    }

    @EventHandler
    public void on(OrderPlacedEvent event) {
        UUID bargainId = event.getBargainId();
        BargainEntity bargain = repository.findById(bargainId).orElseThrow();
        OrderEntity newOrder = orderEntityFactory.eventToOrderEntity(event);
        bargain.addOrder(newOrder);
        repository.save(bargain);
    }
}
