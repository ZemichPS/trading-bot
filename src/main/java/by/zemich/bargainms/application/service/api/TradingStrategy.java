package by.zemich.bargainms.application.service.api;

import by.zemich.bargainms.domain.event.BargainAllowedEvent;
import org.ta4j.core.Rule;

import java.math.BigDecimal;

public interface TradingStrategy {
    enum StrategyType {SPOT, FEATURES}

    void openBargain(BargainAllowedEvent event);
    void complete();

    String getName();
    StrategyType getStrategyType();
    EnterConditionStrategy getEnterRule();
}
