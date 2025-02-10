package by.zemich.bargainms.domain.valueobject;

import java.math.BigDecimal;

public record Amount(BigDecimal origQty, BigDecimal executedQty, BigDecimal cummulativeQuoteQty) {
}
