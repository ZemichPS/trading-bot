package by.zemich.bargainms.application.dto;

import by.zemich.bargainms.domain.valueobject.OrderSide;
import by.zemich.bargainms.domain.valueobject.OrderStatus;
import by.zemich.bargainms.domain.valueobject.OrderType;
import by.zemich.bargainms.domain.valueobject.TimeInForce;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BinanceOrderResponse {
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
