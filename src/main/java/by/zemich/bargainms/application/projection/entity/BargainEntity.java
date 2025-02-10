package by.zemich.bargainms.application.projection.entity;

import by.zemich.bargainms.domain.valueobject.BargainStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "bargains")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BargainEntity {
    @Id
    private UUID uuid;
    private LocalDateTime initTime;
    @OneToMany(
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<OrderEntity> orders;
    private String symbol;
    @Enumerated(EnumType.STRING)
    private BargainStatus bargainStatus;
    private BigDecimal result;
    private String strategyName;

    public void addOrder(OrderEntity order) {
        order.setBargain(this);
        orders.add(order);
    }
}
