package com.todo.cqrs.todo.impl.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.todo.cqrs.lib.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by 6/25/17.
 */
@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "domain_event", schema = "todocqrs")
public class JpaDomainEvent implements Serializable {


    @Tolerate
    public JpaDomainEvent() {

    }

    public JpaDomainEvent(DomainEvent domainEvent) {
        this.domainEvent = domainEvent;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "aggregate_id")
    private String aggregateId;

    @Column(name = "event_details", nullable = false, updatable = false, insertable = true)
    @Type(type = "DomainEventJsonType")
    private DomainEvent domainEvent;

    @JsonIgnore
    @CreatedDate
    @Column(name = "created_on", updatable = false)
    private LocalDateTime createdOn;

    @JsonIgnore
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @JsonIgnore
    @Version
    @Column(columnDefinition = "integer DEFAULT 0", nullable = false)
    private Long version;

    @PrePersist
    public void onSave() {
        this.aggregateId = domainEvent.getAggregateId().id;
        this.setCreatedOn(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
        this.setVersion(1L);
    }

    @PreUpdate
    public void onUpdate() {
        this.setUpdatedAt(LocalDateTime.now());
        long updatedVersion = getVersion();
        updatedVersion += 1;
        this.setVersion(updatedVersion);
    }
}
