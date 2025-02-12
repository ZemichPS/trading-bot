package by.zemich.bargainms.application.service.api;


public abstract class AbstractBaseStrategy implements TradingStrategy {

    private final EnterConditionStrategy enterStrategy;

    protected AbstractBaseStrategy(
            EnterConditionStrategy enterStrategy
    ) {
        this.enterStrategy = enterStrategy;
    }


    @Override
    public EnterConditionStrategy getEnterRule() {
        return enterStrategy;
    }
}
