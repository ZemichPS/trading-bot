package by.zemich.bargainms.domain.event;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
public class BargainAllowedEvent {
    private String symbol;
    private String strategyName;
    private BigDecimal currentAssetPrice;
}
