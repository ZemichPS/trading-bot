package by.zemich.bargainms.domain.agreegate;

import by.zemich.bargainms.domain.command.CreateOrderCommand;
import by.zemich.bargainms.domain.command.UpdateOrderStatusCommand;
import by.zemich.bargainms.domain.event.OrderPlacedEvent;
import by.zemich.bargainms.domain.event.OrderSavedEvent;
import by.zemich.bargainms.domain.event.OrderStatusUpdatedEvent;
import by.zemich.bargainms.domain.event.OrderUpdatedEvent;
import by.zemich.bargainms.domain.valueobject.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class OrderAggregate {
    @AggregateIdentifier
    private Long orderId;
    private UUID bargainId;
    private String clientOrderId;
    private Pair pair;
    private Long transactTime;
    private Price price;
    private Amount amount;
    private OrderStatus status;
    private TimeInForce timeInForce;
    private OrderType type;
    private OrderSide side;

    protected OrderAggregate(){}

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command){
        OrderSavedEvent event = OrderSavedEvent.builder().build();
        BeanUtils.copyProperties(command, event);
        apply(event);
    }

    @EventSourcingHandler
    public void on(OrderSavedEvent event){
        orderId = event.getOrderId();
        bargainId = event.getBargainId();
        clientOrderId = event.getClientOrderId();
        pair = new Pair(event.getSymbol());
        transactTime = event.getTransactTime();
        price = new Price(event.getPrice());
        amount = new Amount(event.getOrigQty(), event.getExecutedQty(), event.getCummulativeQuoteQty());
        status = event.getStatus();
        timeInForce = event.getTimeInForce();
        type = event.getType();
        side = event.getSide();
    }

    @CommandHandler
    public void handle(UpdateOrderStatusCommand command){
        OrderStatusUpdatedEvent event = OrderStatusUpdatedEvent.builder().build();
        BeanUtils.copyProperties(command, event);
        apply(event);
    }

    @EventSourcingHandler
    public void on(OrderStatusUpdatedEvent event){
        this.orderId = event.getOrderId();
        this.price = new Price(event.getPrice());
        this.amount = new Amount(event.getOrigQty(), event.getExecutedQty(), event.getCummulativeQuoteQty());
        this.status = event.getStatus();
    }

}
