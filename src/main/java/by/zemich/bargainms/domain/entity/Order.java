package by.zemich.bargainms.domain.entity;

import by.zemich.bargainms.domain.valueobject.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class Order {
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

    protected Order(){}

    public Order(){

    }
}
