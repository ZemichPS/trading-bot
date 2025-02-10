package by.zemich.bargainms.application.factory;

import by.zemich.bargainms.application.projection.entity.BargainEntity;
import by.zemich.bargainms.domain.event.BargainInitiatedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

@Component
public class BargainEntityFactory {
    public BargainEntity eventToBargainEntity(BargainInitiatedEvent event) {
        return BargainEntity.builder()
                .uuid(UUID.randomUUID())
                .bargainStatus(event.getBargainStatus())
                .result(BigDecimal.ZERO)
                .initTime(event.getStartedAt())
                .strategyName(event.getStrategyName())
                .orders(new ArrayList<>())
                .symbol(event.getSymbol())
                .build();
    }
}
