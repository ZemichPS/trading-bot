package by.zemich.bargainms.domain.event;

import lombok.Data;

@Data
public class EntryPointDetectedEvent {
    private String symbol;
    private String strategyName;
}
