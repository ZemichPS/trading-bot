package by.zemich.bargainms.domain.event;


import by.zemich.bargainms.domain.valueobject.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BargainInitiatedEvent {
    private UUID bargainId;
    private String strategyName;
    private String symbol;
    private BargainStatus bargainStatus;
    private LocalDateTime startedAt;
    private BargainStatus status;
}
