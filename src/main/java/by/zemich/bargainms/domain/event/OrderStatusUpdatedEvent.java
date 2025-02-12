package by.zemich.bargainms.domain.event;

import by.zemich.bargainms.domain.valueobject.OrderStatus;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Builder
public class OrderStatusUpdatedEvent {
    @TargetAggregateIdentifier
    private Long orderId;
    private String symbol;
    private OrderStatus status;
    private BigDecimal price;
    private BigDecimal origQty;
    private BigDecimal executedQty;
    private BigDecimal cummulativeQuoteQty;
}
