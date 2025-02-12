package by.zemich.bargainms.infrastructure.repository.jpa;

import by.zemich.bargainms.infrastructure.repository.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
