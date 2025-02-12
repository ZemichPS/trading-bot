package by.zemich.bargainms.domain.command;

import by.zemich.bargainms.domain.valueobject.TimeInForce;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PlaceBuyOrderCommand {
    private String symbol;
    private BigDecimal price;
    private BigDecimal quantity;
    private TimeInForce timeInForce;
}
