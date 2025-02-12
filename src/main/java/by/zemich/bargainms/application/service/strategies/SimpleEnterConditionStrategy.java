package by.zemich.bargainms.application.service.strategies;

import by.zemich.bargainms.application.service.api.EnterConditionStrategy;
import org.springframework.stereotype.Component;
import org.ta4j.core.BarSeries;

@Component
public class SimpleEnterConditionStrategy implements EnterConditionStrategy {
    @Override
    public boolean satisfiesBy(BarSeries series) {
        return false;
    }
}
