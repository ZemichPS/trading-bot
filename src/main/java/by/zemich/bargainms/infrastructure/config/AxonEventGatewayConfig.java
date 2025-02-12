package by.zemich.bargainms.infrastructure.config;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.gateway.DefaultEventGateway;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class AxonEventGatewayConfig {

    @Bean
    public EventGateway eventGateway(EventBus eventBus) {
        return DefaultEventGateway.builder()
                .eventBus(eventBus)
                .build();
    }

    @Bean
    EventBus eventBus() {
        return SimpleEventBus.builder()
                .build();
    }



}
