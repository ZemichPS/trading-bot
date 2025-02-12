package by.zemich.bargainms.infrastructure.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class AxonCommandGatewayConfig {

    @Bean
    public CommandGateway commandGateway(CommandBus commandBus) {
        return DefaultCommandGateway.builder()
                .commandBus(commandBus)
                .build();
    }

    @Bean
    CommandBus commandBus(TransactionManager transactionManager) {
        return SimpleCommandBus.builder()
                .transactionManager(transactionManager)
                .build();
    }

    @Bean
    public TransactionManager transactionManager(PlatformTransactionManager platformTransactionManager) {
        return new SpringTransactionManager(platformTransactionManager);
    }


}
