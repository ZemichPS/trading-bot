package by.zemich.bargainms.application.saga;

import by.zemich.bargainms.domain.command.InitiateBargainCommand;
import by.zemich.bargainms.domain.event.BargainInitiatedEvent;
import by.zemich.bargainms.domain.valueobject.BargainStatus;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import java.time.LocalDateTime;
import java.util.UUID;

@Saga
@AllArgsConstructor
public class BargainSaga {

    private final CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "symbol")
    public void on(BargainInitiatedEvent event) {
        InitiateBargainCommand command = InitiateBargainCommand.builder()
                .bargainId(UUID.randomUUID())
                .startedAt(LocalDateTime.now())
                .bargainStatus(BargainStatus.NEW)
                .symbol(event.getSymbol())
                .strategyName(event.getStrategyName())
                .build();
        commandGateway.send(command);

    }

    @SagaEventHandler(associationProperty = "symbol")
    public void on(){

    }

}
