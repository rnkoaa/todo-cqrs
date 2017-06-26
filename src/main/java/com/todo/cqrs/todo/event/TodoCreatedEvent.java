package com.todo.cqrs.todo.event;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.todo.cqrs.lib.DomainEvent;
import com.todo.cqrs.todo.TodoId;
import com.todo.cqrs.todo.impl.jpa.TodoDomainEvent;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * Created by 6/21/17.
 */
@Builder
@JsonDeserialize(builder = TodoCreatedEvent.TodoCreatedEventBuilder.class)
public class TodoCreatedEvent extends DomainEvent {
    private final String description;

    public TodoCreatedEvent(String todoId, int nextVersion, String description, long timestamp) {
        super(todoId, nextVersion, timestamp, TodoEvent.CREATED_EVENT);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class TodoCreatedEventBuilder{
        private String aggregateId;
        private int version;
        private long timestamp;
        private String eventType;
        private String description;

        public TodoCreatedEventBuilder aggregateId(String aggregateId) {
            this.aggregateId = aggregateId;
            return this;
        } public TodoCreatedEventBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TodoCreatedEventBuilder version(int version) {
            this.version = version;
            return this;
        }

        public TodoCreatedEventBuilder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public TodoCreatedEventBuilder eventType(String eventType) {
            this.eventType = eventType;
            return this;
        }

        public TodoCreatedEvent build() {
            return new TodoCreatedEvent(aggregateId, version, description, timestamp);
        }
    }
}
