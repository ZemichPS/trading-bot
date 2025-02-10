package by.zemich.bargainms.domain.agreegate;

import by.zemich.bargainms.domain.command.UpdateOrderStatusCommand;
import by.zemich.bargainms.domain.event.OrderStatusUpdatedEvent;
import by.zemich.bargainms.domain.event.OrderUpdatedEvent;
import by.zemich.bargainms.domain.valueobject.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class OrderAggregate {
    @AggregateIdentifier
    private OrderId id;
    private Pair pair;
    private Long transactTime;
    private Price price;
    private Amount amount;
    private OrderStatus status;
    private TimeInForce timeInForce;
    private OrderType type;
    private OrderSide side;

    protected OrderAggregate(){}

    public OrderAggregate(){

    }

    @CommandHandler
    public void handle(UpdateOrderStatusCommand command){
        apply(new OrderUpdatedEvent());
    }

    @EventSourcingHandler
    public void on(OrderUpdatedEvent event){
        this.status = event.getStatus();

    }



}
