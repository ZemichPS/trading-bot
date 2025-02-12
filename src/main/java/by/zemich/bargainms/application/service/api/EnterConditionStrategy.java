package by.zemich.bargainms.application.service.api;

import org.ta4j.core.BarSeries;

public interface EnterConditionStrategy {
    boolean satisfiesBy(BarSeries series);
}
