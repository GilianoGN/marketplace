package dio.marketplace.ticketing.infrastructure.persistence.entity;

import java.time.Instant;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash(value = "seat_locks", timeToLive = 30)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatLockEntity {
    @Id
    private String id;

    @Indexed
    private String customerId;

    private Instant createdAt;
}