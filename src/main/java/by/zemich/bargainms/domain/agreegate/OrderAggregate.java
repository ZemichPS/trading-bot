package by.zemich.bargainms.domain.agreegate;

import by.zemich.bargainms.domain.valueobject.*;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

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
}
