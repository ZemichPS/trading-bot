package by.zemich.bargainms.infrastructure.repository;

import by.zemich.bargainms.application.projection.entity.BargainEntity;
import com.netflix.spectator.impl.UnsafeUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BargainRepository extends JpaRepository<BargainEntity, UUID> {
}
