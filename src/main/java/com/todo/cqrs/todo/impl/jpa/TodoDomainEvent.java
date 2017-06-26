package com.todo.cqrs.todo.impl.jpa;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.todo.event.TodoEvent;
import lombok.Builder;
import lombok.experimental.Tolerate;

/**
 * Created on 6/26/2017.
 */
@Builder
@JsonDeserialize(builder = TodoDomainEvent.TodoDomainEventBuilder.class)
public class TodoDomainEvent extends DomainEvent {

    @Tolerate
    public TodoDomainEvent() {
        super(null, 0, 0L, null);
    }

    protected TodoDomainEvent(String aggregateId, int version, long timestamp, TodoEvent eventType) {
        super(aggregateId, version, timestamp, eventType);
    }

    public TodoDomainEvent(DomainEvent domainEvent) {
        super(domainEvent.getAggregateId(), domainEvent.getVersion(), domainEvent.getTimestamp(), domainEvent.getEventType());
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class TodoDomainEventBuilder {

        private String aggregateId;
        private int version;
        private long timestamp;
        private String eventType;

        public TodoDomainEventBuilder aggregateId(String aggregateId) {
            this.aggregateId = aggregateId;
            return this;
        }

        public TodoDomainEventBuilder version(int version) {
            this.version = version;
            return this;
        }

        public TodoDomainEventBuilder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public TodoDomainEventBuilder eventType(String eventType) {
            this.eventType = eventType;
            return this;
        }

        public TodoDomainEvent build() {
            return new TodoDomainEvent(aggregateId, version, timestamp, TodoEvent.valueOf(eventType));
        }
    }
}
