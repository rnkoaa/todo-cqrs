package com.todo.cqrs.todo.impl.jpa;

import com.todo.cqrs.lib.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;

/**
 * Created by 6/25/17.
 */
@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "domain_event")
public class JpaDomainEvent {


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
    private DomainEvent domainEvent;

    @PrePersist
    public void updateAggregateId() {
        this.aggregateId = domainEvent.getAggregateId().id;
    }
}
