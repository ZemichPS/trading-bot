package by.zemich.bargainms.domain.command;

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
public class InitiateBargainCommand {
    private UUID bargainId;
    private String strategyName;
    private String symbol;
    private BargainStatus bargainStatus;
    private LocalDateTime startedAt;
}
