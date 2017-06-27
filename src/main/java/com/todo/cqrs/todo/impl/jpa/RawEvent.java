package com.todo.cqrs.todo.impl.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created on 6/27/2017.
 */
@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "domain_event", schema = "todocqrs")
public class RawEvent implements Serializable {

    @CreatedDate
    @Column(name = "created_time", updatable = false, nullable = false)
    private LocalDateTime createdTime;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "domain_event_generator")
    @SequenceGenerator(name = "domain_event_generator",
            sequenceName = "domain_event_id_seq", allocationSize = 50)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "aggregate_id")
    private String aggregateId;

    @Column(name = "event_type", nullable = false, updatable = false)
    private String eventType;

    @Column(name = "payload", nullable = false, updatable = false)
    private String payload;

    @Tolerate
    RawEvent() {
    }

    public RawEvent(String aggregateId, String eventType, String payload) {
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.payload = payload;
    }

    @PrePersist
    public void onSave() {
        this.createdTime = LocalDateTime.now();
    }

}
