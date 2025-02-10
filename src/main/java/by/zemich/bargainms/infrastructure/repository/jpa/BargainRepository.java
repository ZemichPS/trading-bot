package by.zemich.bargainms.infrastructure.repository.jpa;

import by.zemich.bargainms.application.projection.entity.BargainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BargainRepository extends JpaRepository<BargainEntity, UUID> {
}
