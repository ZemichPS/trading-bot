package by.zemich.bargainms.application.saga;

import by.zemich.bargainms.application.dto.OrderResponseDto;
import by.zemich.bargainms.application.service.ExchangeService;
import by.zemich.bargainms.domain.command.AddOrderToBargainCommand;
import by.zemich.bargainms.domain.event.BargainInitiatedEvent;
import lombok.AllArgsConstructor;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

@Saga
@AllArgsConstructor
public class OrderSaga {
    private final ExchangeService exchangeService;

    @StartSaga
    @SagaEventHandler(associationProperty = "symbol")
    public void on(BargainInitiatedEvent event){
        String symbol = event.getSymbol();
        OrderResponseDto newOrder = exchangeService.createNewOrder(symbol);
        AddOrderToBargainCommand commaned = AddOrderToBargainCommand.builder()
                .build();
    }

    @SagaEventHandler(associationProperty = "symbol")
    public void on(){

    }
}
