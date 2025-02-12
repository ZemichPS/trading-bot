package by.zemich.bargainms.domain.request;

import by.zemich.bargainms.domain.valueobject.OrderSide;
import by.zemich.bargainms.domain.valueobject.OrderType;
import by.zemich.bargainms.domain.valueobject.TimeInForce;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreateOrderRequest {
    private String symbol;
    private OrderSide side;
    private OrderType type;
    private TimeInForce timeInForce;
    private BigDecimal quantity;
    private BigDecimal quoteOrderQty;
    private BigDecimal price;
    private BigDecimal stopPrice;
}
