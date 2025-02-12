package by.zemich.bargainms.application.service;

import by.zemich.bargainms.domain.event.BargainAllowedEvent;
import by.zemich.bargainms.domain.event.EntryPointDetectedEvent;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RiskManager {

    private final EventGateway eventGateway;

    @EventHandler
    public void on(EntryPointDetectedEvent event){
        // TODO логика проверки
        BargainAllowedEvent bargainAllowedEvent = new BargainAllowedEvent();
        BeanUtils.copyProperties(event, bargainAllowedEvent);
        eventGateway.publish(bargainAllowedEvent);
    }
}
