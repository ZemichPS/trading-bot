package by.zemich.bargainms.application.saga;

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
    public void on(OrderPlacedEvent event){

    }

    @SagaEventHandler(associationProperty = "symbol")
    public void on(){

    }


}
