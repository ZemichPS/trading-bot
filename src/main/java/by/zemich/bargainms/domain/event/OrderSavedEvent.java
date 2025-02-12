package by.zemich.bargainms.domain.event;


import by.zemich.bargainms.domain.valueobject.OrderSide;
import by.zemich.bargainms.domain.valueobject.OrderStatus;
import by.zemich.bargainms.domain.valueobject.OrderType;
import by.zemich.bargainms.domain.valueobject.TimeInForce;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderSavedEvent {
    private UUID bargainId;
    private String symbol;
    private Long orderId;
    private Long orderListId;
    private String clientOrderId;
    private Long transactTime;
    private BigDecimal price;
    private BigDecimal origQty;
    private BigDecimal executedQty;
    private BigDecimal origQuoteOrderQty;
    private BigDecimal cummulativeQuoteQty;
    private OrderStatus status;
    private TimeInForce timeInForce;
    private OrderType type;
    private OrderSide side;
    private Long workingTime;
    private String selfTradePreventionMode;
}
