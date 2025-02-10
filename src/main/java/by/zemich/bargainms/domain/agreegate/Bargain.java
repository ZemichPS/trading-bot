package by.zemich.bargainms.domain.agreegate;

import by.zemich.bargainms.domain.command.InitiateBargainCommand;
import by.zemich.bargainms.domain.event.BargainInitiatedEvent;
import by.zemich.bargainms.domain.valueobject.BargainId;
import by.zemich.bargainms.domain.valueobject.Pair;
import by.zemich.bargainms.domain.valueobject.BargainStatus;
import by.zemich.bargainms.domain.valueobject.Strategy;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Bargain {
    @AggregateIdentifier
    private BargainId id;
    private LocalDateTime initTime;
    private List<OrderAggregate> orders;
    private Pair pair;
    private BargainStatus bargainStatus;
    private BigDecimal result;
    private Strategy strategy;

    protected Bargain() {
    }

    @CommandHandler
    public Bargain(InitiateBargainCommand command) {
        apply(BargainInitiatedEvent.builder()
                .bargainId(command.getBargainId())
                .bargainStatus(command.getBargainStatus())
                .symbol(command.getSymbol())
                .startedAt(command.getStartedAt())
                .strategyName(command.getStrategyName())
                .build());
    }

    @EventSourcingHandler
    public void on(BargainInitiatedEvent event) {
        this.id = new BargainId(event.getBargainId());
        this.orders = new ArrayList<>();
        this.initTime = event.getStartedAt();
        this.pair = new Pair(event.getSymbol());
        this.bargainStatus = event.getBargainStatus();
        this.strategy = new Strategy(event.getStrategyName());
    }

}
