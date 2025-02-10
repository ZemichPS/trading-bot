package by.zemich.bargainms.infrastructure.repository.jpa;

import by.zemich.bargainms.application.projection.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
