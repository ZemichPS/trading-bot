package by.zemich.bargainms.domain.event;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EntryPointDetectedEvent {
    private String symbol;
    private String strategyName;
    private BigDecimal currentAssetPrice;
}
