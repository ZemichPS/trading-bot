package by.zemich.bargainms.application.service.strategies;

import by.zemich.bargainms.application.dto.BinanceOrderResponse;
import by.zemich.bargainms.application.service.CryptoMarketService;
import by.zemich.bargainms.application.service.api.AbstractBaseStrategy;
import by.zemich.bargainms.application.service.api.EnterConditionStrategy;
import by.zemich.bargainms.domain.command.CreateOrderCommand;
import by.zemich.bargainms.domain.command.PlaceBuyOrderCommand;
import by.zemich.bargainms.domain.command.UpdateOrderStatusCommand;
import by.zemich.bargainms.domain.event.BargainAllowedEvent;
import by.zemich.bargainms.domain.event.OrderPlacedEvent;
import by.zemich.bargainms.domain.event.OrderStatusChangedEvent;
import by.zemich.bargainms.domain.request.CreateOrderRequest;
import by.zemich.bargainms.domain.command.InitiateBargainCommand;
import by.zemich.bargainms.domain.valueobject.BargainStatus;
import by.zemich.bargainms.domain.valueobject.OrderSide;
import by.zemich.bargainms.domain.valueobject.OrderType;
import by.zemich.bargainms.domain.valueobject.TimeInForce;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.ta4j.core.Rule;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RefreshScope
@ConditionalOnProperty(
        prefix = "strategies.simple-spot-strategies",
        name = "enabled",
        havingValue = "true"
)
@Saga
public class SimpleSpotStrategy extends AbstractBaseStrategy {

    @Value("${strategies.simple-spot-strategies.trade-amount}")
    private BigDecimal tradeAmount;
    @Value("${strategies.simple-spot-strategies.percentage-interest}")
    private BigDecimal percentageInterest;

    private final CryptoMarketService marketService;
    private final CommandGateway commandGateway;

    public SimpleSpotStrategy(
            CryptoMarketService marketService,
            CommandGateway commandGateway,
            EnterConditionStrategy enterStrategy
    ) {
        super(enterStrategy);
        this.marketService = marketService;
        this.commandGateway = commandGateway;
    }

    @Override
    @StartSaga
    @SagaEventHandler(associationProperty = "symbol")
    public void openBargain(BargainAllowedEvent event) {
        BigDecimal assetPrice = event.getCurrentAssetPrice();
        BigDecimal quantity = tradeAmount.divide(assetPrice, 5, RoundingMode.HALF_DOWN);

        InitiateBargainCommand initBargainCommand = InitiateBargainCommand.builder()
                .bargainId(UUID.randomUUID())
                .bargainStatus(BargainStatus.NEW)
                .strategyName(event.getStrategyName())
                .symbol(event.getSymbol())
                .startedAt(LocalDateTime.now())
                .build();

        PlaceBuyOrderCommand placeBuyOrderCommand = PlaceBuyOrderCommand.builder()
                .symbol(event.getSymbol())
                .price(event.getCurrentAssetPrice())
                .quantity(quantity)
                .timeInForce(TimeInForce.GTC)
                .build();
        commandGateway.send(initBargainCommand);
        commandGateway.send(placeBuyOrderCommand);
    }

    @SagaEventHandler(associationProperty = "symbol")
    public void on(OrderPlacedEvent event) {
        CreateOrderCommand command = CreateOrderCommand.builder().build();
        BeanUtils.copyProperties(event, command);
        commandGateway.send(command);
    }

    @SagaEventHandler(associationProperty = "symbol")
    public void on(OrderStatusChangedEvent event) {
        UpdateOrderStatusCommand command = UpdateOrderStatusCommand.builder().build();
        BeanUtils.copyProperties(event, command);
        commandGateway.send(command);

        if (event.getSide().equals(OrderSide.BUY)) {

            BigDecimal buyPrice = event.getPrice();
            BigDecimal percentageValue = buyPrice.multiply(percentageInterest).divide(new BigDecimal("100"), 5, RoundingMode.HALF_UP);
            BigDecimal aimSellingPrice = buyPrice.add(percentageValue);

            BinanceOrderResponse binanceOrderResponse = marketService.placeSellLimitOrder(
                    event.getSymbol(),
                    aimSellingPrice,
                    event.getExecutedQty(),
                    TimeInForce.GTC
            );
        }
    }

    @Override
    @SagaEventHandler(associationProperty = "symbol")
    public void complete() {

    }

    @Override
    public String getName() {
        return "Simple spot strategy";
    }

    @Override
    public StrategyType getStrategyType() {
        return StrategyType.SPOT;
    }


}
