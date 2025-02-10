package by.zemich.bargainms.domain.event;

import by.zemich.bargainms.domain.valueobject.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderStatusUpdatedEvent {
    private Long orderId;
    private OrderStatus status;
    private BigDecimal price;
    private BigDecimal origQty;
    private BigDecimal executedQty;
    private BigDecimal cummulativeQuoteQty;
}
