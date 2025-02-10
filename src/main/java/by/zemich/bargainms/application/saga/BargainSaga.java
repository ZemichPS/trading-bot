package by.zemich.bargainms.application.saga;

import by.zemich.bargainms.application.service.ExchangeService;
import by.zemich.bargainms.domain.command.InitiateBargainCommand;
import by.zemich.bargainms.domain.event.EntryPointDetectedEvent;
import by.zemich.bargainms.domain.valueobject.BargainStatus;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.UUID;

@Saga
@AllArgsConstructor
public class BargainSaga {

    private final CommandGateway commandGateway;


    @StartSaga
    @SagaEventHandler(associationProperty = "symbol")
    public void on(EntryPointDetectedEvent event) {
        InitiateBargainCommand command = InitiateBargainCommand.builder()
                .bargainId(UUID.randomUUID())
                .startedAt(LocalDateTime.now())
                .bargainStatus(BargainStatus.NEW)
                .symbol(event.getSymbol())
                .strategyName(event.getStrategyName())
                .build();
        SagaLifecycle.associateWith("symbol", event.getSymbol());
        commandGateway.send(command);
    }

    @SagaEventHandler(associationProperty = "symbol")
    public void on(){

    }

}
