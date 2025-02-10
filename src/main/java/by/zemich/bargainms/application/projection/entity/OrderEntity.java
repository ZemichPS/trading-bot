package by.zemich.bargainms.application.projection.entity;

import by.zemich.bargainms.domain.valueobject.*;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderEntity {
    @Id
    private UUID uuid;
    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = BargainEntity.class
    )
    private BargainEntity bargain;
    private Long orderId;
    private String clientOrderId;
    private String symbol;
    private Long transactTime;
    private String asset;
    private BigDecimal price;
    private BigDecimal origQty;
    private BigDecimal executedQty;
    private BigDecimal cummulativeQuoteQty;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Enumerated(EnumType.ORDINAL)
    private TimeInForce timeInForce;
    @Enumerated(EnumType.ORDINAL)
    private OrderType type;
    @Enumerated(EnumType.ORDINAL)
    private OrderSide side;
}
