package com.todo.cqrs.todo.impl.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.IOException;
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


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "domain_event_generator")
    @SequenceGenerator(name = "domain_event_generator",
            sequenceName = "domain_event_id_seq", allocationSize = 50)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "aggregate_id")
    private String aggregateId;


    @Column(name = "event_details", nullable = false, updatable = false)
    @Type(type = "DomainEventJsonType")
    private TodoDomainEvent domainEvent;

    @Column(name = "event_type", nullable = false, updatable = false)
    private String eventType;

    @JsonIgnore
    @CreatedDate
    @Column(name = "created_on", updatable = false)
    private LocalDateTime createdOn;


    @Tolerate
    public JpaDomainEvent() {

    }

    public JpaDomainEvent(TodoDomainEvent domainEvent) {
        this.domainEvent = domainEvent;
    }

    @PrePersist
    public void onSave() {
        this.aggregateId = domainEvent.getAggregateId();
        this.eventType = domainEvent.getEventType().name();
        this.setCreatedOn(LocalDateTime.now());
    }

    public static class JpaDomainEventDeserializer extends JsonDeserializer<JpaDomainEvent> {

        @Override
        public JpaDomainEvent deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            ObjectMapper mapper = (ObjectMapper) jp.getCodec();
            return null;
        }
    }
}
