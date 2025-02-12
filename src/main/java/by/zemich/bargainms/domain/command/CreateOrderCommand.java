package by.zemich.bargainms.domain.command;

import by.zemich.bargainms.domain.valueobject.OrderSide;
import by.zemich.bargainms.domain.valueobject.OrderStatus;
import by.zemich.bargainms.domain.valueobject.OrderType;
import by.zemich.bargainms.domain.valueobject.TimeInForce;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class CreateOrderCommand {
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
