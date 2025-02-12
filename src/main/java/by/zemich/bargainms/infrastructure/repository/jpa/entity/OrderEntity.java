package by.zemich.bargainms.infrastructure.repository.jpa.entity;

import by.zemich.bargainms.domain.valueobject.*;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderEntity {
    @Id
    private Long orderId;
    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = BargainEntity.class
    )
    private BargainEntity bargain;
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
