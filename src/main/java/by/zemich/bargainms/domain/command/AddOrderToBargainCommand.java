package by.zemich.bargainms.domain.command;

import by.zemich.bargainms.domain.valueobject.OrderSide;
import by.zemich.bargainms.domain.valueobject.OrderStatus;
import by.zemich.bargainms.domain.valueobject.OrderType;
import by.zemich.bargainms.domain.valueobject.TimeInForce;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AddOrderToBargainCommand  {
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
