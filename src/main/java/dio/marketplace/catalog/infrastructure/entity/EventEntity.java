package dio.marketplace.catalog.infrastructure.entity;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import dio.marketplace.catalog.infrastructure.event.EventListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "events")
@EntityListeners(EventListener.class)
@Data
@RequiredArgsConstructor
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private Instant date;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdOn;
}
