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
public class SomeOrderEvent {
    private String strategyName;
    private Long orderId;
    private String clientOrderId;
    private String symbol;
    private Long transactTime;
    private String asset;
    private BigDecimal price;
    private BigDecimal origQty;
    private BigDecimal executedQty;
    private BigDecimal cummulativeQuoteQty;
    private OrderStatus orderStatus;
    private TimeInForce timeInForce;
    private OrderType type;
    private OrderSide side;
}
